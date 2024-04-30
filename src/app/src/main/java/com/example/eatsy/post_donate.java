package com.example.eatsy;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.Toast;


import java.io.IOException;
import java.util.UUID;


import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class post_donate extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageButton uploadImage;
    private Uri filePath;
    private StorageReference storageReference;
    private final ImageButton go_back = findViewById(R.id.leftArrowButton);
    private final RadioGroup radioGroup = findViewById(R.id.radioGroupQuantity);
    private final EditText quantityEditText = findViewById(R.id.editTextOther);
    private final EditText titleEditText = findViewById(R.id.title_editText);
    private final EditText descriptionEditText = findViewById(R.id.description_editText);
    private final EditText pickupTimeEditText = findViewById(R.id.pickuptime_editText);
    private final EditText addressEditText = findViewById(R.id.Address_editText);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseApp.initializeApp(this);

        // Set up Firebase App Check with the Play Integrity provider
        FirebaseAppCheck.getInstance().installAppCheckProviderFactory(PlayIntegrityAppCheckProviderFactory.getInstance());
        storageReference = FirebaseStorage.getInstance().getReference();
        setContentView(R.layout.activity_post_donate);
//        ImageButton go_back = findViewById(R.id.leftArrowButton);
//        RadioGroup radioGroup = findViewById(R.id.radioGroupQuantity);
//        final EditText quantityEditText = findViewById(R.id.editTextOther);
//        EditText titleEditText = findViewById(R.id.title_editText);
//        EditText descriptionEditText = findViewById(R.id.description_editText);
//        EditText pickupTimeEditText = findViewById(R.id.pickuptime_editText);
//        EditText addressEditText = findViewById(R.id.Address_editText);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.radioButtonOther) {
                    quantityEditText.setVisibility(View.VISIBLE);
                } else {
                    quantityEditText.setVisibility(View.GONE);
                }
            }
        });
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Button post = findViewById(R.id.button_post);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputs()) {
                    uploadImage();
                    addPostToFirbase();
                    Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        uploadImage = findViewById(R.id.cameraButton);
        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
    }
    private void openGallery() {
        Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(galleryIntent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                uploadImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void uploadImage() {
        if (filePath != null) {
            StorageReference ref = storageReference.child("user_post_img/" + UUID.randomUUID().toString());
            ref.putFile(filePath);
        }
    }
    private boolean validateInputs() {
        boolean valid = true;
        int selectedId = radioGroup.getCheckedRadioButtonId();
        if (selectedId == -1) {
            valid = false;
        }else if(selectedId == R.id.radioButtonOther){
            if (quantityEditText.getText().toString().trim().isEmpty()) {
                quantityEditText.setError("Please enter a quantity or select a button");
                valid = false;
            }
        }
        if (titleEditText.getText().toString().trim().isEmpty()) {
            titleEditText.setError("Required");
            valid = false;
        }

        if (descriptionEditText.getText().toString().trim().isEmpty()) {
            descriptionEditText.setError("Required");
            valid = false;
        }

        if (quantityEditText.getText().toString().trim().isEmpty()) {
            quantityEditText.setError("Required");
            valid = false;
        }

        if (pickupTimeEditText.getText().toString().trim().isEmpty()) {
            pickupTimeEditText.setError("Required");
            valid = false;
        }

        if (addressEditText.getText().toString().trim().isEmpty()) {
            addressEditText.setError("Required");
            valid = false;
        }

        if (filePath == null) {
            Toast.makeText(this, "Please upload an image", Toast.LENGTH_SHORT).show();
            valid = false;
        }

        return valid;
    }
    private void addPostToFirbase(){
        String quantity = quantityEditText.getText().toString().trim();
        String title = titleEditText.getText().toString().trim();
        String description = descriptionEditText.getText().toString().trim();
        String image = filePath.toString();
        String pick_up_times = pickupTimeEditText.getText().toString().trim();
        //TODO revise these four string
        String userID = "someUserID";
        String userName = "someUserName";
        String latitude = "111";
        String longtitude = "222";
        factory_donate post = new factory_donate(userName, title, description, quantity, pick_up_times,latitude, longtitude, image);
        post.saveToFirebase();
    }

}