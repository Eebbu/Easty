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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import java.io.IOException;
import java.util.UUID;


import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Post of donation page
 * @author Zihan Yuan(u7773880)
 * @author Boxuan Lin(u7705128)
 */
public class post_donate extends post_base {
    @Override
    // Method to get layout resource ID specific to donation posts
    protected int getLayoutResourceId() {
        return R.layout.activity_post_donate;
    }
    // Setup specific views for donation post activity
    @Override
    protected void setupSpecificViews() {
        pickupTimeEditText = findViewById(R.id.pickuptime_editText);
        uploadImage = findViewById(R.id.cameraButton);
    }
    // Validate inputs for donation post form
    @Override
    protected boolean validateInputs() {
        boolean valid = true;
        int selectedId = radioGroup.getCheckedRadioButtonId();
        if (selectedId == -1) {
            valid = false;
            Toast.makeText(this, "Please enter a quantity or select a button", Toast.LENGTH_SHORT).show();
        }else if(selectedId == R.id.radioButtonOther){
            if (quantityEditText.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Please enter a quantity or select a button", Toast.LENGTH_SHORT).show();
                valid = false;
            }
        }
        if (titleEditText.getText().toString().trim().isEmpty()) {
            titleEditText.setError("Required");
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
    // Add the donation post to Firebase
    protected void addPostToFirbase(){
        String quantity;
        int selectedId = radioGroup.getCheckedRadioButtonId();
        if(selectedId == R.id.radioButtonOther){
            quantity = quantityEditText.getText().toString().trim();
        }else{
            RadioButton radioButton = findViewById(selectedId);
            quantity = radioButton.getText().toString();
        }
        String title = titleEditText.getText().toString().trim();
        String description = "";
        if (null != descriptionEditText) {
            description = descriptionEditText.getText().toString().trim();
        }
        String image = filePath.toString();
        String pick_up_times = pickupTimeEditText.getText().toString().trim();
        //TODO revise these four string
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userEmail = user.getEmail(); // Get current user's email address
        String userName = DataManager.getDataInstance().searchUserName(userEmail);

        String latitude = selectedLatitude;
        String longtitude = selectedLongitude;
        StorageReference ref = storageReference.child("user_post_img/" + UUID.randomUUID().toString());
        factory_donate post = new factory_donate(userName, title, description,
                quantity, pick_up_times,latitude, longtitude, image,filePath,ref);
        post.saveToFirebase();
    }

}