package com.example.eatsy.pages;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eatsy.Factory_donate;
import com.example.eatsy.Factory_exchange;
import com.example.eatsy.Factory_wanted;
import com.example.eatsy.Post;
import com.example.eatsy.R;
import com.example.eatsy.datamanagement.FireStoreHelper;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;

// Protected UI components accessible by child classes
public abstract class Post_base extends AppCompatActivity {
    protected ImageButton uploadImage;
    protected Uri filePath;
    protected StorageReference storageReference;
    protected RadioGroup radioGroup;
    protected EditText quantityEditText;
    protected EditText titleEditText;
    protected EditText descriptionEditText;
    protected EditText pickupTimeEditText;
    protected TextView addressText;
    protected EditText wantEditText;
    protected String selectedLatitude;
    protected String selectedLongitude;

    // Request codes for intents
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int PICK_MAP_REQUEST = 2;
    // Abstract methods to be implemented by child classes for post submission and validation
    protected Post addPostToFirebase(){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userName = DashboardActivity.users.get(user.getEmail()).getUsername();

        //post type?

        //post_title
        String postTitle = titleEditText.getText().toString().trim();

        //post_description
        String description = "";
        if (null != descriptionEditText) {
            description = descriptionEditText.getText().toString().trim();
        }

        //quantity
        String quantity;
        int selectedId = radioGroup.getCheckedRadioButtonId();
        if(selectedId == R.id.radioButtonOther){
            quantity = quantityEditText.getText().toString().trim();
        }else{
            RadioButton radioButton = findViewById(selectedId);
            quantity = radioButton.getText().toString();
        }


        String latitude = selectedLatitude;
        String longitude = selectedLongitude;

        if (this.getClass() == Post_wanted.class){
            Factory_wanted post = new Factory_wanted(userName,postTitle,description,quantity,latitude,longitude);
            DashboardActivity.postsToShow.add(0,post);
            DashboardActivity.adapter.notifyDataSetChanged();
            return post;
        }


        String pick_up_times = "";
        if (null != pickupTimeEditText) {
            pick_up_times = pickupTimeEditText.getText().toString().trim();
        }


        String image = filePath.toString();

        if (this.getClass() == Post_donate.class) {
            Factory_donate post = new Factory_donate(userName, postTitle, description,
                    quantity, pick_up_times, latitude, longitude, image, filePath.toString());
            DashboardActivity.postsToShow.add(0,post);
            DashboardActivity.adapter.notifyDataSetChanged();
            return post;
        }


        String wantInExchange;
        wantInExchange = wantEditText.getText().toString().trim();

        Factory_exchange post = new Factory_exchange(userName,postTitle,description,
                wantInExchange,quantity,pick_up_times,latitude,longitude,image,filePath.toString());
        DashboardActivity.postsToShow.add(0,post);
        DashboardActivity.adapter.notifyDataSetChanged();
        return post;
    }
    protected abstract boolean validateInputs();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(this instanceof Post_donate || this instanceof Post_exchange)
            initializeFirebase();
        setContentView(getLayoutResourceId());
        setupViews();
        setupListeners();
    }
    // Initialize Firebase and related services
    private void initializeFirebase() {
        FirebaseApp.initializeApp(this);
        FirebaseAppCheck.getInstance().installAppCheckProviderFactory(PlayIntegrityAppCheckProviderFactory.getInstance());
        storageReference = FirebaseStorage.getInstance().getReference();
    }
    // Abstract method to get the layout resource ID from child classes
    protected abstract int getLayoutResourceId();

    // Setup common views and listeners for all post types
    private void setupViews() {
        ImageButton go_back = findViewById(R.id.leftArrowButton);
        radioGroup = findViewById(R.id.radioGroupQuantity);
        quantityEditText = findViewById(R.id.editTextOther);
        titleEditText = findViewById(R.id.title_editText);
        descriptionEditText = findViewById(R.id.description_editText);
        addressText = findViewById(R.id.Address_editText);
        setupSpecificViews();
        go_back.setOnClickListener(v -> finish());
    }
    protected void setupSpecificViews() {}

    private void setupListeners() {
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioButtonOther) {
                quantityEditText.setVisibility(View.VISIBLE);
            } else {
                quantityEditText.setVisibility(View.GONE);
            }
        });

        Button post = findViewById(R.id.button_post);
        post.setOnClickListener(v -> {
            if (validateInputs()) {
                Post newPost = addPostToFirebase();
                FireStoreHelper.createAndPost(newPost);
                finish();
            }
        });

        Button choose = findViewById(R.id.confirm_button);
        choose.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MapSelection.class);
            startActivityForResult(intent,PICK_MAP_REQUEST);
        });

        if ((this instanceof Post_donate) || this instanceof Post_exchange) {
            uploadImage.setOnClickListener(v -> openGallery());
        }

    }

    // Intent to open gallery and select an image
    protected void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(Intent.createChooser(galleryIntent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                int newDp = (int) (100 * getResources().getDisplayMetrics().density);
                Bitmap resizedBitmap = scaleBitmapToMaxSize(bitmap, newDp);
                uploadImage.setImageBitmap(resizedBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (requestCode == PICK_MAP_REQUEST && resultCode == RESULT_OK) {
            if (data != null) {
                double[] selectedPoint = data.getDoubleArrayExtra("selectedPoint");
                assert selectedPoint != null;
                selectedLatitude = String.valueOf(selectedPoint[0]);
                selectedLongitude = String.valueOf(selectedPoint[1]);
                String selectedAddress = data.getStringExtra("selectedAddress");
                addressText.setText(selectedAddress);


            }
        }
    }
    // Scale bitmap to a maximum size to fit into a specific dimension
    private Bitmap scaleBitmapToMaxSize(Bitmap originalBitmap, int maxSizePx) {
        if (originalBitmap == null) {
            return null;
        }

        int width = originalBitmap.getWidth();
        int height = originalBitmap.getHeight();
        float ratio = (float) width / height;

        int scaledWidth = maxSizePx;
        int scaledHeight = maxSizePx;

        if (ratio > 1) {
            scaledHeight = (int) (maxSizePx / ratio);
        } else {
            scaledWidth = (int) (maxSizePx * ratio);
        }

        return Bitmap.createScaledBitmap(originalBitmap, scaledWidth, scaledHeight, true);
    }
}