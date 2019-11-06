package csci310.ng.scott.usclassifieds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NotificationsActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener;
    private BottomNavigationView navigation;
    public final String TAG = "NotificationActivity";

    // UI Elements
    ListView mylist;
    private TextView results;
    // Firebase stuff
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth.AuthStateListener authStateListener;
    private List<Notification> notifications = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        // Link UI
        mylist = (ListView) findViewById(R.id.list_notifications);
        results = findViewById(R.id.results_not);

        // Get Firebase refs
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        navigation = findViewById(R.id.navigationNote);
        mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_market:
                        Intent j = new Intent(NotificationsActivity.this, MarketActivity.class);
                        startActivity(j);
                        return true;
                    case R.id.navigation_notifications:
                        return true;
                    case R.id.navigation_profile:
                        Intent k = new Intent(NotificationsActivity.this, ProfileActivity.class);
                        startActivity(k);
                        return true;
                }
                return false;
            }
        };
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //set selected item depending on Activity
        navigation.setSelectedItemId(R.id.navigation_notifications);

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser currUser = firebaseAuth.getCurrentUser();
                if (currUser == null) {
                    Toast.makeText(NotificationsActivity.this, "Signed Out", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), LandingActivity.class));
                }
            }
        };

        DatabaseReference rootRef = firebaseDatabase.getReference().child("Notification");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                notifications.clear();

                for (DataSnapshot dss : dataSnapshot.getChildren()) {
                    Notification notification = dss.getValue(Notification.class);
                    Log.d(TAG, "Notifications is " + notification.getMessage());
                    if (notification.getReceiverUid().equals(firebaseAuth.getCurrentUser().getUid())) {
                        notifications.add(notification);
                    }
                }
                Log.d(TAG, "size of notifications is " + notifications.size());
                // item adapter here
                NotificationListAdapter adapter = new NotificationListAdapter(getApplicationContext(), R.layout.layout_notification, notifications);
                mylist.setAdapter(adapter);
                if(notifications.size() == 0){
                    results.setVisibility(View.VISIBLE);
                }
                else{
                    results.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        navigation.setSelectedItemId(R.id.navigation_notifications);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(authStateListener != null) {
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }
}
