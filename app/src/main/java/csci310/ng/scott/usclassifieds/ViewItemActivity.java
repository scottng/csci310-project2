package csci310.ng.scott.usclassifieds;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class ViewItemActivity extends Activity {

    // UI Elements
    ImageView viewItemImage;
    TextView viewItemName;
    TextView viewItemPrice;
    TextView viewItemDescription;
    ImageView viewProfilePicture;
    TextView textProfileName;
    Button buttonMarkSold;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Link UI
        viewItemImage = findViewById(R.id.view_item_img);
        viewItemName = findViewById(R.id.view_item_name);
        viewItemPrice = findViewById(R.id.view_item_price);
        viewItemDescription = findViewById(R.id.view_item_description);
        viewProfilePicture = findViewById(R.id.view_profile_picture);
        textProfileName = findViewById(R.id.text_profile_name);
        buttonMarkSold = findViewById(R.id.button_mark_sold);

        // YOU CAN SET THIS BUTTON'S VISIBILITY
        buttonMarkSold.setVisibility(View.VISIBLE);
    }
}
