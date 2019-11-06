package csci310.ng.scott.usclassifieds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewItemActivity extends AppCompatActivity {

    public final String TAG = "ViewItemActivity";

    //Declare UI views
    private TextView textViewTitle;
    private TextView textViewDescription;
    private TextView textViewPrice;
    private ImageView imageViewItemPic;
    private TextView textViewUserName;
    private TextView textViewEmail;
    private Button markSoldButton;
    private ImageButton upButton;
    private CircleImageView circleImageViewUserPic;
    private Item item;
    private User sellerUser;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);

        //Instantiate the views
        textViewTitle = findViewById(R.id.view_item_name);
        textViewDescription = findViewById(R.id.view_item_description);
        textViewPrice = findViewById(R.id.view_item_price);
        imageViewItemPic = findViewById(R.id.view_item_img);
        textViewUserName = findViewById(R.id.text_profile_name);
        textViewEmail = findViewById(R.id.text_email);
        circleImageViewUserPic = findViewById(R.id.view_profile_picture);
        markSoldButton = findViewById(R.id.button_mark_sold);
        upButton = findViewById(R.id.button_up);

        //Get source intent
        Intent i = getIntent();
        item = (Item) i.getSerializableExtra("Item");

        //Set up views
        textViewTitle.setText(item.getTitle());
        textViewDescription.setText(item.getDescription());
        textViewPrice.setText(String.format("$%.2f", item.getPrice()));
        Glide.with(getApplicationContext()).load(item.getPhotoURL())
                .into(imageViewItemPic);

        Log.d(TAG, "Item " + item.getItemID() + " is " + item.getTitle() + " sold by " + item.getSellerID());

        //get User object from firebase pull
        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser currUser= firebaseAuth.getCurrentUser();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //set UI elements on callback
                sellerUser = dataSnapshot.child("User").child(item.getSellerID()).getValue(User.class);
                textViewUserName.setText(sellerUser.getFullName());
                textViewEmail.setText(sellerUser.getEmail());

                //if Current user is viewing there own item allow them to sell
                if(sellerUser.getUserID().equals(currUser.getUid())){
                    markSoldButton.setVisibility(Button.VISIBLE);
                }

                //Put images into ImageViews
                if (!sellerUser.getProfilePic().equals("")) {
                    Glide.with(getApplicationContext())
                            .load(sellerUser.getProfilePic()).into(circleImageViewUserPic);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });

        //Set Listener on Circular Profile Pic
        circleImageViewUserPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewItemActivity.this, OtherProfileActivity.class);
                intent.putExtra("User", sellerUser);
                startActivity(intent);
            }
        });

        //set upButton Listener to return to previous Activity
        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        markSoldButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItem(item.getItemID());
            }
        });

    }

    public void deleteItem(String itemId){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Item").child(itemId);
        databaseReference.removeValue();
        Toast.makeText(this, "Item has been sold!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
