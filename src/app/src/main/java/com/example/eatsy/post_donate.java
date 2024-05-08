package com.example.eatsy;

import android.widget.RadioButton;
import android.widget.Toast;


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

        if (addressText.getText().toString().trim().isEmpty()) {
            addressText.setError("Required");
            valid = false;
        }

        if (filePath == null) {
            Toast.makeText(this, "Please upload an image", Toast.LENGTH_SHORT).show();
            valid = false;
        }

        return valid;
    }
    // Add the donation post to Firebase

//

}