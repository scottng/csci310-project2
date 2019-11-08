package csci310.ng.scott.usclassifieds;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class OtherProfileActivity extends Activity {

    // Firebase stuff
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    public static final String TAG = "OtherProfileActivity";

    private Button searchUser;
    CircleImageView imageProfilePicture;
    TextView textProfileName;
    TextView textProfileBio;
    TextView textProfileBodyEmail;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener;
    private BottomNavigationView navigation;
    private Button followUser;
    private boolean follow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_profile);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        Intent i = getIntent();
        final User user = (User) i.getSerializableExtra("User");

        imageProfilePicture = findViewById(R.id.image_other_profile_picture);
        textProfileName = findViewById(R.id.text_other_profile_name);
        textProfileBio = findViewById(R.id.text_other_profile_bio);
        searchUser = findViewById(R.id.other_search_user);
        followUser = findViewById(R.id.button_follow);
        textProfileBodyEmail = findViewById(R.id.text_other_profile_body_email);
        navigation= findViewById(R.id.navigation);
        mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_market:
                        Intent j = new Intent(OtherProfileActivity.this, MarketActivity.class);
                        startActivity(j);
                        return true;
                    case R.id.navigation_notifications:
                        Intent k = new Intent(OtherProfileActivity.this, NotificationsActivity.class);
                        startActivity(k);
                        return true;
                    case R.id.navigation_profile:
                        Intent m = new Intent(OtherProfileActivity.this, ProfileActivity.class);
                        startActivity(m);
                        return true;
                }
                return false;
            }
        };
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        textProfileName.setText(user.getFullName());
        if (!user.getTextBio().equals("")) {
            textProfileBio.setText(user.getTextBio());
        }
        textProfileBodyEmail.setText(user.getEmail());
        if (!user.getProfilePic().equals("")) {
            Glide.with(getApplicationContext())
                    .load(user.getProfilePic()).into(imageProfilePicture);
        }
        searchUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OtherProfileActivity.this, MarketActivity.class);
                intent.putExtra("User", user);
                startActivity(intent);
            }
        });

        final DatabaseReference dbref = firebaseDatabase.getReference();
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User currUser = dataSnapshot.child("User").child(firebaseAuth.getCurrentUser().getUid()).getValue(User.class);
                if (currUser.isFriendsWith(user.getUserID())) {
                    follow = true;
                } else {
                    follow = false;
                }

                if(dataSnapshot.child("Notification").hasChild(currUser.getUserID() + "-" + user.getUserID())) {
                    followUser.setClickable(false);
                    followUser.setText("Requested");
                } else if (follow) {
                    followUser.setClickable(true);
                    // da fuq
                    followUser.setText("My friend");
                } else {
                    followUser.setClickable(true);
                    followUser.setText("Follow");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        followUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FirebaseUser currUser = firebaseAuth.getCurrentUser();
                final DatabaseReference dbref = firebaseDatabase.getReference();
                if (follow) {
                    dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            User currUserInfo = dataSnapshot.child("User").child(currUser.getUid()).getValue(User.class);
                            currUserInfo.removeFriend(user.getUserID());
                            user.removeFriend(currUserInfo.getUserID());

                            dbref.child("User").child(currUserInfo.getUserID()).setValue(currUserInfo);
                            dbref.child("User").child(user.getUserID()).setValue(user);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                } else {
                    // Log.d(TAG, "curr user info is displayName " + currUser.getDisplayName() + " with email " + currUser.getEmail());
                    dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            User currUserInfo = dataSnapshot.child("User").child(currUser.getUid()).getValue(User.class);

                            Notification notification = new Notification(currUserInfo.getUserID(), "", user.getUserID(), currUserInfo.getFullName() + " requested to follow you.");
                            if (TextUtils.isEmpty(currUserInfo.getProfilePic())) {
                                notification.setSenderImgURL(currUserInfo.getProfilePic());
                            }
                            firebaseDatabase.getReference("Notification").child(currUser.getUid() + "-" + user.getUserID()).setValue(notification)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()) {
                                                Toast.makeText(OtherProfileActivity.this, "Your request has been sent.", Toast.LENGTH_SHORT).show();
                                                followUser.setClickable(false);
                                                followUser.setText("Requested");
                                            }
                                        }
                                    });
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
    }

}
