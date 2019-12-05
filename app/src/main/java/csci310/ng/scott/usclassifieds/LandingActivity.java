package csci310.ng.scott.usclassifieds;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LandingActivity extends AppCompatActivity {

    private static final String TAG = "LandingActivity";
    public static final int RESULT_EXIT = -1;

    // UI Elements
    private Button buttonCreateAccount;
    private Button buttonDirecttoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        setContentView(R.layout.activity_landing);


        // Link UI
        buttonCreateAccount = findViewById(R.id.button_create_account);
        buttonDirecttoLogin = findViewById(R.id.button_directto_login);

        // Set button listeners
        buttonCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CreateAccountActivity.class);
                startActivityForResult(i, CreateAccountActivity.REQUEST_CODE);
            }
        });

        buttonDirecttoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivityForResult(i, LoginActivity.REQUEST_CODE);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_EXIT){
            finish();
        }
    }
}
