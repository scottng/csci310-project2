package csci310.ng.scott.usclassifieds;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;

import de.hdodenhof.circleimageview.CircleImageView;

public class CreateAccountActivity extends AppCompatActivity {

    private static final String TAG = "CreateAccountActivity";
    public static final int REQUEST_CODE = 2;
    public static final int PICK_IMAGE=3;

    private FirebaseAuth mAuth;

    // UI Elements
    private EditText editTextFullName;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private EditText editTextBio;
    private Button buttonRegister;
    private CircleImageView imageButtonProfilePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        // Link UI
        editTextFullName = findViewById(R.id.edit_text_full_name);
        editTextEmail = findViewById(R.id.edit_text_email);
        editTextPassword = findViewById(R.id.edit_text_password);
        editTextConfirmPassword = findViewById(R.id.edit_text_confirm_password);
        editTextBio = findViewById(R.id.edit_text_bio);
        buttonRegister = findViewById(R.id.button_register);
        imageButtonProfilePicture = findViewById(R.id.image_button_profile_picture);


        //set up Listener for the profile picture selector button
        imageButtonProfilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Picture For Your Profile"), PICK_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //on return from picking an image for profile
        if (requestCode== PICK_IMAGE  && resultCode == RESULT_OK && data!=null) {
            Uri profilePicUri = data.getData();
            imageButtonProfilePicture.setImageURI(profilePicUri);
        }
    }
}
