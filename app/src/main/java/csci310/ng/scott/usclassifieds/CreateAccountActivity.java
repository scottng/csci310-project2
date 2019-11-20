package csci310.ng.scott.usclassifieds;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

public class CreateAccountActivity extends AppCompatActivity {

    private static final String TAG = "CreateAccountActivity";
    public static final int REQUEST_CODE = 2;
    public static final int PICK_IMAGE=3;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference databaseReference;
    private StorageReference mStorageRef;

    // UI Elements
    private EditText editTextFullName;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private EditText editTextBio;
    private Button buttonRegister;
    private CircleImageView imageButtonProfilePicture;
    private Uri profilePictureUri=null;

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

        //set up register button listener
        databaseReference = FirebaseDatabase.getInstance().getReference("User");

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fullName = editTextFullName.getText().toString();
                final String email = editTextEmail.getText().toString();
                final String password = editTextPassword.getText().toString();
                final String confirmPassword = editTextConfirmPassword.getText().toString();
                final String textBio = editTextBio.getText().toString();

                // Check if input is valid
                CreateAccountValidator cav = new CreateAccountValidator();

                if(cav.invalidInput(fullName, password, email, confirmPassword)) {
                    Toast.makeText(CreateAccountActivity.this, cav.getErrorMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }

                // Let user know they're registering
                Toast.makeText(CreateAccountActivity.this, "Registering...", Toast.LENGTH_SHORT).show();

                // create user
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(CreateAccountActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    final User userInfo = new User(fullName, email, textBio);

                                    if(profilePictureUri != null) {
                                        mStorageRef = FirebaseStorage.getInstance().getReference().child("profile_pics");
                                        final StorageReference imageFilePath = mStorageRef.child(mAuth.getCurrentUser().getUid());
                                        imageFilePath.putFile(profilePictureUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                            @Override
                                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                                imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                    @Override
                                                    public void onSuccess(Uri uri) {
                                                        UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
                                                                .setDisplayName(fullName)
                                                                .setPhotoUri(uri)
                                                                .build();

                                                        mAuth.getCurrentUser().updateProfile(profileUpdate)
                                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                        // successfully added pic and user
                                                                    }
                                                                });

                                                        userInfo.setProfilePic(uri.toString());

                                                    }
                                                });
                                            }
                                        });
                                    }

                                    userInfo.setUserID(mAuth.getCurrentUser().getUid());

                                    databaseReference.child(mAuth.getCurrentUser().getUid())
                                            .setValue(userInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(CreateAccountActivity.this, "Registration Complete", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(), MarketActivity.class));
                                        }
                                    });

                                } else {
                                    Toast.makeText(CreateAccountActivity.this, "Failed to Register", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //on return from picking an image for profile
        if (requestCode== PICK_IMAGE  && resultCode == RESULT_OK && data!=null) {
            profilePictureUri = data.getData();
            imageButtonProfilePicture.setImageURI(profilePictureUri);
        }
    }

}
