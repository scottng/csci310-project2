package csci310.ng.scott.usclassifieds;

import android.content.Context;
import android.text.Layout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
        final String senderUid = getItem(position).getSenderUid();
        final String recUid = getItem(position).getReceiverUid();

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(mresource, parent, false);
        }

        TextView messD = (TextView) convertView.findViewById(R.id.list_notification_message);
        messD.setText(message);

        if (!TextUtils.isEmpty(img)) {
            Glide.with(mcontext).load(img)
                    .into((ImageView) convertView.findViewById(R.id.list_notification_image));
        }

        Button acceptButton = (Button) convertView.findViewById(R.id.list_notification_button_accept);
        Button declineButton = (Button) convertView.findViewById(R.id.list_notification_button_decline);

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference dbref = FirebaseDatabase.getInstance().getReference();
                dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        final User sendUser;
                        final User recUser;

                        sendUser = dataSnapshot.child("User").child(senderUid).getValue(User.class);
                        recUser = dataSnapshot.child("User").child(recUid).getValue(User.class);

                        sendUser.addFriend(recUid);
                        recUser.addFriend(senderUid);

                        dbref.child("User").child(senderUid).setValue(sendUser);
                        dbref.child("User").child(recUid).setValue(recUser);

                        dbref.child("Notification").child(senderUid + "-" + recUid).removeValue();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        declineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("Notification");
                dbref.child(senderUid + "-" + recUid).removeValue();
            }
        });

        return convertView;
    }
}
