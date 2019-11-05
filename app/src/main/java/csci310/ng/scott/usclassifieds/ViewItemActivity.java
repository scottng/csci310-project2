package csci310.ng.scott.usclassifieds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class ViewItemActivity extends AppCompatActivity {

    public final String TAG = "ViewItemActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);

        Intent i = getIntent();
        Item item = (Item) i.getSerializableExtra("Item");

        Log.d(TAG, "Item " + item.getItemID() + " is " + item.getTitle() + " sold by " + item.getSellerID());
    }
}
