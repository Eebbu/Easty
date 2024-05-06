package com.example.eatsy;

import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.storage.StorageReference;

import java.util.UUID;
/**
 * Post of exchange page
 * @author Zihan Yuan(u7773880)
 */
public class post_exchange extends post_base {
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
    // Add the exchange post to Firebase
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
        String want = wantEditText.getText().toString().trim();
        //TODO revise these four string
//        String userName = "someUserName";
        String userName = getIntent().getStringExtra("USERNAME");
        String latitude = selectedLatitude;
        String longtitude = selectedLongitude;
        StorageReference ref = storageReference.child("user_post_img/" + UUID.randomUUID().toString());
        factory_exchange post = new factory_exchange(userName, title, description,want, quantity,
                                        pick_up_times,latitude, longtitude, image,filePath,ref);
        post.saveToFirebase();
    }

}