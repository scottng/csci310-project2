package csci310.ng.scott.usclassifieds;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.List;

public class MarketActivity extends AppCompatActivity {

    private static final String TAG = "MarketActivity";
    public static final int REQUEST_CODE = 3;

    private TextView mTextMessage;
    private GridView gridViewItems;
    private FloatingActionButton fabAddItem;

    private ItemAdapter adapter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_market:
                    mTextMessage.setText(R.string.title_market);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.navigation_profile:
                    mTextMessage.setText(R.string.title_profile);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        // Link bottom navigation
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Link UI elements
        gridViewItems = findViewById(R.id.grid_items);
        fabAddItem = findViewById(R.id.fab_add_item);

        fabAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SellActivity.class);
                startActivityForResult(i, SellActivity.REQUEST_CODE);
            }
        });



    }

    private class ItemAdapter extends ArrayAdapter<Item> {
        private List<Item> items;

        public ItemAdapter(Context context, int resource, List<Item> objects){
            super(context, resource, objects);
            this.items = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.layout_item, null);
            }

            Item curr = items.get(position);

            TextView textTitle = convertView.findViewById(R.id.list_item_name);
            TextView textPrice = convertView.findViewById(R.id.list_item_price);
            TextView textDistance = convertView.findViewById(R.id.list_item_distance);

            textTitle.setText(curr.getTitle());
            textPrice.setText(Double.toString(curr.getPrice()));

            return convertView;
        }

//        @Override
//        public long getItemId(int position) {
//            return getItem(position).getId();
//        }
    }

}
