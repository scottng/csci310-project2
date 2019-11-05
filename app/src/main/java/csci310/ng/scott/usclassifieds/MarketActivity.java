package csci310.ng.scott.usclassifieds;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.GridView;
import androidx.appcompat.widget.SearchView;
import android.widget.TextView;

import java.util.List;

public class MarketActivity extends AppCompatActivity {

    private static final String TAG = "MarketActivity";
    public static final int REQUEST_CODE = 3;

    // UI Elements
    // private TextView mTextMessage;
    private GridView gridViewItems;
    private FloatingActionButton fabAddItem;
    private Button buttonFilter;
    private SearchView searchMarket;

    private ItemAdapter adapter;
    private BottomNavigationView navigation;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_market:
                    // mTextMessage.setText(R.string.title_market);
                    return true;
                case R.id.navigation_notifications:
                    // mTextMessage.setText(R.string.title_notifications);
                    Intent j = new Intent(MarketActivity.this, NotificationsActivity.class);
                    startActivity(j);
                    return true;
                case R.id.navigation_profile:
                    // mTextMessage.setText(R.string.title_profile);
                    Intent k = new Intent(MarketActivity.this, ProfileActivity.class);
                    startActivity(k);
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
        // mTextMessage = (TextView) findViewById(R.id.message);
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //set selected item depending on Activity
        navigation.setSelectedItemId(R.id.navigation_market);

        // Link UI elements
        gridViewItems = findViewById(R.id.grid_items);
        fabAddItem = findViewById(R.id.fab_add_item);
        buttonFilter = findViewById(R.id.button_filter);
        searchMarket = findViewById(R.id.search_market);

        // Set FAB listener
        fabAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SellActivity.class);
                startActivityForResult(i, SellActivity.REQUEST_CODE);
            }
        });

        // Set filter button listener
        buttonFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(getApplicationContext(), FilterActivity.class);
                startActivityForResult(j, FilterActivity.REQUEST_CODE);
            }
        });

        searchMarket.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


    }

    // Adapter for items
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

    }

    // Not sure what this does
    @Override
    protected void onResume() {
        super.onResume();
        navigation.setSelectedItemId(R.id.navigation_market);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //on return from picking an image for profile
        if (requestCode== FilterActivity.REQUEST_CODE  && resultCode == RESULT_OK && data!=null) {
            data.getIntExtra(FilterActivity.SELECTED_FILTER, -1);

            // get extra from intent that holds the user selected filter option

            // update search results to display the filtered items


        }
    }


}
