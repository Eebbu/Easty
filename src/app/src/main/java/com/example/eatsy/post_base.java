package com.example.eatsy;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;

public abstract class post_base extends AppCompatActivity {
    protected ImageButton uploadImage;
    protected Uri filePath;
    protected StorageReference storageReference;
    protected RadioGroup radioGroup;
    protected EditText quantityEditText;
    protected EditText titleEditText;
    protected EditText descriptionEditText;
    protected EditText pickupTimeEditText;
    protected EditText addressEditText;
    protected EditText wantEditText;
    private static final int PICK_IMAGE_REQUEST = 1;
    protected abstract void addPostToFirbase();
    protected abstract boolean validateInputs();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(this instanceof post_donate || this instanceof post_exchange)
            initializeFirebase();
        setContentView(getLayoutResourceId());
        setupViews();
        setupListeners();
    }

    private void initializeFirebase() {
        FirebaseApp.initializeApp(this);
        FirebaseAppCheck.getInstance().installAppCheckProviderFactory(PlayIntegrityAppCheckProviderFactory.getInstance());
        storageReference = FirebaseStorage.getInstance().getReference();
    }

    protected abstract int getLayoutResourceId();

//    protected void setupViews();
    private void setupViews() {
        ImageButton go_back = findViewById(R.id.leftArrowButton);
        radioGroup = findViewById(R.id.radioGroupQuantity);
        quantityEditText = findViewById(R.id.editTextOther);
        titleEditText = findViewById(R.id.title_editText);
        descriptionEditText = findViewById(R.id.description_editText);
        addressEditText = findViewById(R.id.Address_editText);
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
                addPostToFirbase();
                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });
        if ((this instanceof post_donate) || this instanceof post_exchange) {
            uploadImage.setOnClickListener(v -> openGallery());
        }

    }


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
    }

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