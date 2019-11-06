package csci310.ng.scott.usclassifieds;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class NotificationListAdapter extends ArrayAdapter<Notification> {
    int mresource;
    Context mcontext;
    public NotificationListAdapter(@NonNull Context context, int resource, @NonNull List<Notification> objects) {
        super(context, resource, objects);
        mresource = resource;
        mcontext = context;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Log.d("NotificationListAdapter", "inside of NotificationListAdapter getView()");
        String message = getItem(position).getMessage();
        String img = getItem(position).getSenderImgURL();
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(mresource, parent, false);
        }

        TextView messD = (TextView) convertView.findViewById(R.id.list_notification_message);
        messD.setText(message);
        return convertView;
    }
}
