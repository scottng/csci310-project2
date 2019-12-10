package csci310.ng.scott.usclassifieds;


import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;



public class CustomInfoWindow implements GoogleMap.InfoWindowAdapter {

    private Context context;
    private static final String TAG = CustomInfoWindow.class.getSimpleName();


    public CustomInfoWindow(Context ctx){
        context = ctx;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View view = ((Activity)context).getLayoutInflater()
                .inflate(R.layout.layout_infowindow, null);
        TextView title = view.findViewById(R.id.title);
        TextView desc = view.findViewById(R.id.description);
        TextView price = view.findViewById(R.id.price);
        ImageView img = view.findViewById(R.id.picture);

        Item item = (Item)marker.getTag();

        if (item == null){
            return null;
        }

        title.setText(item.getTitle());

//        int imageId = context.getResources().getIdentifier(item.getPhotoURL(),
//                "drawable", context.getPackageName());
//        img.setImageResource(imageId);


        Log.d(TAG, "about to load pic: " + item.getPhotoURL());

        Glide.with(context).load(item.getPhotoURL()).into(img);



        desc.setText(item.getDescription());
        double num = item.getPrice();
        String s = String.format("%.2f", num);
        String holder = "$" + s;
        price.setText(holder);

        return view;
    }
}
