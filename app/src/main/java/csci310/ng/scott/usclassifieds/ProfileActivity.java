package csci310.ng.scott.usclassifieds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {

    // UI Elements
    CircleImageView imageProfilePicture;
    TextView textProfileName;
    TextView textProfileBio;
    TextView textProfileBodyEmail;
    TextView textProfileBodyPhone;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener;
    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Link UI
        imageProfilePicture = findViewById(R.id.image_profile_picture);
        textProfileName = findViewById(R.id.text_profile_name);
        textProfileBio = findViewById(R.id.text_profile_bio);
        textProfileBodyEmail = findViewById(R.id.text_profile_body_email);
        textProfileBodyPhone = findViewById(R.id.text_profile_body_phone);
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

    }

    @Override
    protected void onResume() {
        super.onResume();
        navigation.setSelectedItemId(R.id.navigation_profile);
    }
}
