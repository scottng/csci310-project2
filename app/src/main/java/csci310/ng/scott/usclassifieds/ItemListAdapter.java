package csci310.ng.scott.usclassifieds;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.List;

class ItemListAdapter extends ArrayAdapter<Item> {

    public ItemListAdapter(@NonNull Context context, int resource, @NonNull List<Item> objects) {
        super(context, resource, objects);
    }
}
