package csci310.ng.scott.usclassifieds;

import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

import android.os.Bundle;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    // UI Elements
    CircleImageView imageProfilePicture;
    TextView textProfileName;
    TextView textProfileBio;
    TextView textProfileBodyEmail;
    TextView textProfileBodyPhone;

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


    }
}
