package csci310.ng.scott.usclassifieds;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.ImageViewTargetFactory;

import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

class ItemListAdapter extends ArrayAdapter<Item> {
    private int mresource;
    private Context mcontext;
    public ItemListAdapter(@NonNull Context context, int resource, @NonNull List<Item> objects) {
        super(context, resource, objects);
        mcontext = context;
        mresource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String description = getItem(position).getDescription();
        String title = getItem(position).getTitle();
        double price = getItem(position).getPrice();
        String img = getItem(position).getPhotoURL();

        LayoutInflater inflater = LayoutInflater.from(mcontext);
        convertView = inflater.inflate(mresource, parent, false);
        TextView titleView = (TextView) convertView.findViewById(R.id.list_item_name);
        TextView priceView = (TextView) convertView.findViewById(R.id.list_item_price);
        Glide.with(mcontext).load(img).into((ImageView) convertView.findViewById(R.id.image_item));
        titleView.setText(title);
        priceView.setText("$" + Double.toString(price));
        return convertView;
    }
}
