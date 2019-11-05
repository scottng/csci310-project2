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
        Item item1 = new Item("1", "1", "bike", "this is my bike", 34.43, false, "https://slack-redir.net/link?url=https%3A%2F%2Ffirebasestorage.googleapis.com%2Fv0%2Fb%2Fusclassifieds-1ac3c.appspot.com%2Fo%2Fitem_pics%252FBdy4EvB4b4eEhPFxNjqCYNX07eV2-1572925402757%3Falt%3Dmedia%26token%3D3774fe4a-05fe-487c-9ab6-8e7d64864129");
        Item item2 = new Item("2", "2", "dog", "this is my dog", 1, false, "https://slack-redir.net/link?url=https%3A%2F%2Ffirebasestorage.googleapis.com%2Fv0%2Fb%2Fusclassifieds-1ac3c.appspot.com%2Fo%2Fitem_pics%252FBdy4EvB4b4eEhPFxNjqCYNX07eV2-1572925402757%3Falt%3Dmedia%26token%3D3774fe4a-05fe-487c-9ab6-8e7d64864129");
        Item item3 = new Item("3", "3", "cat", "this is my cat", 0.5, false, "https://slack-redir.net/link?url=https%3A%2F%2Ffirebasestorage.googleapis.com%2Fv0%2Fb%2Fusclassifieds-1ac3c.appspot.com%2Fo%2Fitem_pics%252FBdy4EvB4b4eEhPFxNjqCYNX07eV2-1572925402757%3Falt%3Dmedia%26token%3D3774fe4a-05fe-487c-9ab6-8e7d64864129");
        ArrayList<Item> itemList = new ArrayList<Item>();
        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item3);
        ItemListAdapter adapter = new ItemListAdapter(this, R.layout.layout_item, itemList);
        list.setAdapter(adapter);
    }
}
