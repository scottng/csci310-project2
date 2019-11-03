package csci310.ng.scott.usclassifieds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateAccountActivity extends AppCompatActivity {

    private static final String TAG = "CreateAccountActivity";
    public static final int REQUEST_CODE = 2;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference databaseReference;

    // UI Elements
    private EditText editTextFullName;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private EditText editTextBio;
    private Button buttonRegister;

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

        databaseReference = FirebaseDatabase.getInstance().getReference("User");

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fullName = editTextFullName.getText().toString();
                final String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                String confirmPassword = editTextConfirmPassword.getText().toString();
                final String textBio = editTextBio.getText().toString();

                // check field is not empty
                if (TextUtils.isEmpty(fullName)) {
                    Toast.makeText(CreateAccountActivity.this, "Please Enter Full Name", Toast.LENGTH_SHORT).show();
                }



                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(CreateAccountActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    User userInfo = new User(fullName, email, textBio);

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
}
