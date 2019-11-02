package csci310.ng.scott.usclassifieds;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SellActivity extends AppCompatActivity {

    private static final String TAG = "SellActivity";
    public static final int REQUEST_CODE = 4;

    Button buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        buttonCancel = findViewById(R.id.button_cancel_sell);

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
