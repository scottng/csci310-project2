package csci310.ng.scott.usclassifieds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class FilterActivity extends AppCompatActivity {

    private static final String TAG = "FilterActivity";
    public static final String SELECTED_FILTER = "selected_filter";
    public static final int REQUEST_CODE = 400;

    // UI elements
    Button buttonFilterCancel;
    RadioGroup radioGroupFilterCategory;
    RadioGroup radioGroupSort;
    Button buttonFilterApply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        // Link UI
        buttonFilterCancel = findViewById(R.id.button_filter_cancel);
        radioGroupFilterCategory = findViewById(R.id.radio_group_filter_category);
        radioGroupSort = findViewById(R.id.radio_group_sort);
        buttonFilterApply = findViewById(R.id.button_filter_apply);

        // Cancel button should finish activity
        buttonFilterCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Apply button should put the selected filters into the intent and then return
        buttonFilterApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getIntent();

                int categoryIndex = radioGroupFilterCategory.indexOfChild(findViewById(radioGroupFilterCategory.getCheckedRadioButtonId()));
                i.putExtra("categoryIndex", categoryIndex);

                int sortIndex = radioGroupSort.indexOfChild(findViewById(radioGroupSort.getCheckedRadioButtonId()));
                i.putExtra("sortIndex", sortIndex);

                setResult(RESULT_OK, i);

                finish();
            }
        });
    }
}
