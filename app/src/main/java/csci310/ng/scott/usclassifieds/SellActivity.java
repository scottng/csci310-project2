package csci310.ng.scott.usclassifieds;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class SellActivity extends AppCompatActivity {

    private static final String TAG = "SellActivity";
    public static final int REQUEST_CODE = 4;
    public static final int PICK_IMAGE = 3;

    // Profile pic URI
    private Uri itemPicUri;
    private StorageReference mStorageRef;

    // current location info
    private FusedLocationProviderClient fusedLocationClient;
    private LatLng loc;

    // UI Elements
    Button buttonCancel;
    ImageView imageButtonItemPicture;
    EditText editTextTitle;
    EditText editTextPrice;
    EditText editTextDescription;
    Button buttonPostItem;
    RadioGroup radioGroupSellCategory;

    private double lat;
    private double lng;

    private FirebaseAuth mAuth;
    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        // set up location
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // create location for selling item
        fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    lat = location.getLatitude();
                    lng = location.getLongitude();
//                    loc = new LatLng(lat, lng);
                }
            }
        });

        // Get mAuth
        mAuth = FirebaseAuth.getInstance();

        // Get dbref
        dbRef = FirebaseDatabase.getInstance().getReference("Item");

        // Link UI
        buttonCancel = findViewById(R.id.button_cancel_sell);
        imageButtonItemPicture = findViewById(R.id.image_button_item_picture);
        editTextTitle = findViewById(R.id.edit_text_title);
        editTextPrice = findViewById(R.id.edit_text_price);
        editTextDescription = findViewById(R.id.edit_text_description);
        buttonPostItem = findViewById(R.id.button_post_item);
        radioGroupSellCategory = (RadioGroup)findViewById(R.id.radio_group_sell_category);

        // Cancel button listener
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Picture selector button listener
        imageButtonItemPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Picture For Your Profile"), PICK_IMAGE);
            }
        });

        // Post item button listener
        buttonPostItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check length
                if(editTextTitle.getText().toString().trim().length() > 18) {
                    Toast.makeText(getApplicationContext(), getString(R.string.title_length_toast), Toast.LENGTH_LONG).show();
                } else if (editTextDescription.getText().toString().trim().length() > 100) {
                    Toast.makeText(getApplicationContext(), getString(R.string.description_length_toast), Toast.LENGTH_LONG).show();
                } else if (editTextPrice.getText().toString().trim().length() > 9) {
                    Toast.makeText(getApplicationContext(), getString(R.string.item_too_expensive_toast), Toast.LENGTH_LONG).show();
                } else if(editTextTitle.getText().toString().trim().length() == 0) {
                    Toast.makeText(getApplicationContext(), getString(R.string.title_blank_toast), Toast.LENGTH_LONG).show();
                } else if (editTextPrice.getText().toString().trim().length() == 0) {
                    Toast.makeText(getApplicationContext(), getString(R.string.price_blank_toast), Toast.LENGTH_LONG).show();
                } else if (editTextDescription.getText().toString().trim().length() == 0) {
                    Toast.makeText(getApplicationContext(), getString(R.string.description_empty_toast), Toast.LENGTH_LONG).show();
                } else if (itemPicUri == null) {
                    Toast.makeText(getApplicationContext(), getString(R.string.picture_empty_toast), Toast.LENGTH_LONG).show();
                } else {

                    final Item mItem = new Item();

                    // ADD PHOTO AND ITEM TO DATABASE
                    final String currentTime = String.valueOf(System.currentTimeMillis());

                    mItem.setItemID(mAuth.getCurrentUser().getUid() + "-" + currentTime);
                    mItem.setSellerID(mAuth.getCurrentUser().getUid());
                    mItem.setTitle(editTextTitle.getText().toString());
                    mItem.setPrice(Double.parseDouble(editTextPrice.getText().toString()));
                    mItem.setDescription(editTextDescription.getText().toString());
                    mItem.setSold(false);
                    mItem.setLat(lat);
                    mItem.setLng(lng);



                    int index = radioGroupSellCategory.indexOfChild(findViewById(radioGroupSellCategory.getCheckedRadioButtonId()));
                    mItem.setCategory(index);

                    // Create storage ref name
                    mStorageRef = FirebaseStorage.getInstance().getReference().child("item_pics");
                    final StorageReference imageFilePath = mStorageRef.child(mAuth.getCurrentUser().getUid() + "-" + currentTime);


                    imageFilePath.putFile(itemPicUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    mItem.setPhotoURL(uri.toString());

                                    dbRef.child(mItem.getItemID())
                                            .setValue(mItem).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(SellActivity.this, "Your item has been posted!", Toast.LENGTH_SHORT).show();
                                            finish();
                                        }
                                    });
                                }
                            });
                        }
                    });


                }

            }
        });

    }

    // Respond to result of picture selector activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //on return from picking an image for profile
        if (requestCode== PICK_IMAGE  && resultCode == RESULT_OK && data!=null) {
            itemPicUri = data.getData();
            imageButtonItemPicture.setImageURI(itemPicUri);
        }
    }
}