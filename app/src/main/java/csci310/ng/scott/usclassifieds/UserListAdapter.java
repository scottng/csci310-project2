package csci310.ng.scott.usclassifieds;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.List;

public class UserListAdapter extends ArrayAdapter<User> {
    private int mresource;
    private Context mcontext;
    public UserListAdapter(@NonNull Context context, int resource, @NonNull List<User> objects) {
        super(context, resource, objects);
        mresource = resource;
        mcontext = context;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String img = getItem(position).getProfilePic();
        String fullName = getItem(position).getFullName();

        LayoutInflater inflater = LayoutInflater.from(mcontext);
        convertView = inflater.inflate(mresource, parent, false);
        TextView nameView = (TextView) convertView.findViewById(R.id.user_full_name);
        if(!TextUtils.isEmpty(img)) {
            Glide.with(mcontext).load(img).into((ImageView) convertView.findViewById(R.id.user_img));
        }
        nameView.setText(fullName);
        return convertView;
    }
}
