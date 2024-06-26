package com.example.eatsy.pages;

import android.widget.Toast;

import com.example.eatsy.R;

/**
 * Post of exchange page
 * @author Zihan Yuan(u7773880)
 */
public class Post_exchange extends Post_base {
    // Method to get layout resource ID for exchange posts
    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_post_exchange;
    }
    // Setup specific views for exchange post activity
    @Override
    protected void setupSpecificViews() {
        pickupTimeEditText = findViewById(R.id.pickuptime_editText);
        uploadImage = findViewById(R.id.cameraButton);
        wantEditText = findViewById(R.id.want_editText);
    }
    // Validate inputs for exchange post form
    @Override
    protected boolean validateInputs() {
        boolean valid = true;
        int selectedId = radioGroup.getCheckedRadioButtonId();
        // Validation for want input, title, pick-up times, address, and image upload
        if (selectedId == -1) {
            Toast.makeText(this, "Please enter a quantity or select a button", Toast.LENGTH_SHORT).show();
            valid = false;
        }else if(selectedId == R.id.radioButtonOther){
            if (quantityEditText.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Please enter a quantity or select a button", Toast.LENGTH_SHORT).show();
                valid = false;
            }
        }
        // Validation for want input, title, pick-up times, address, and image upload
        if(wantEditText.getText().toString().trim().isEmpty()){
            wantEditText.setError("Required");
            valid = false;
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

}