package csci310.ng.scott.usclassifieds;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;


import androidx.annotation.Nullable;

import java.util.ArrayList;

public class OtherProfileActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView list = (ListView) findViewById(R.id.list_items_in_other_profile);
         ArrayList<Item> itemList = new ArrayList<Item>();

        ItemListAdapter adapter = new ItemListAdapter(this, R.layout.layout_item, itemList);
        list.setAdapter(adapter);
    }
}
