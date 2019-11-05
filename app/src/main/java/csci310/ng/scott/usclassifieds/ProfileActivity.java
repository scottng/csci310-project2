package csci310.ng.scott.usclassifieds;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    // Firebase stuff
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth.AuthStateListener authStateListener;
    public static final int REQUEST_CODE = 123;

    // UI Elements
    private Button searchUser;
    CircleImageView imageProfilePicture;
    TextView textProfileName;
    TextView textProfileBio;
    TextView textProfileBodyEmail;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener;
    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        // Link UI
        imageProfilePicture = findViewById(R.id.image_profile_picture);
        textProfileName = findViewById(R.id.text_profile_name);
        textProfileBio = findViewById(R.id.text_profile_bio);
        searchUser = findViewById(R.id.search_user);
        textProfileBodyEmail = findViewById(R.id.text_profile_body_email);
        navigation= findViewById(R.id.navigation);
        mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_market:
                        Intent j = new Intent(ProfileActivity.this, MarketActivity.class);
                        startActivity(j);
                        return true;
                    case R.id.navigation_notifications:
                        Intent k = new Intent(ProfileActivity.this, NotificationsActivity.class);
                        startActivity(k);
                        return true;
                    case R.id.navigation_profile:
                        return true;
                }
                return false;
            }
        };
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //set selected item depending on Activity
        navigation.setSelectedItemId(R.id.navigation_profile);

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser currUser = firebaseAuth.getCurrentUser();
                if (currUser == null) {
                    Toast.makeText(ProfileActivity.this, "Signed Out", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), LandingActivity.class));
                }
            }
        };
        final User[] currUserInfo = new User[1];
        final FirebaseUser currUser = firebaseAuth.getCurrentUser();
        DatabaseReference rootRef = firebaseDatabase.getReference();
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currUserInfo[0] = dataSnapshot.child("User").child(currUser.getUid()).getValue(User.class);

                textProfileName.setText(currUserInfo[0].getFullName());
                if (!currUserInfo[0].getTextBio().equals("")) {
                    textProfileBio.setText(currUserInfo[0].getTextBio());
                }
                textProfileBodyEmail.setText(currUserInfo[0].getEmail());
                if (!currUserInfo[0].getProfilePic().equals("")) {
                    Glide.with(getApplicationContext())
                            .load(currUserInfo[0].getProfilePic()).into(imageProfilePicture);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        searchUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, MarketActivity.class);
                intent.putExtra("User", currUserInfo[0]);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        navigation.setSelectedItemId(R.id.navigation_profile);
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
