package com.example.eatsy;

import android.widget.Toast;

/**
 * Post of wanted page
 * @author Zihan Yuan(u7773880)
 */
public class Post_wanted extends Post_base {

    // Method to get layout resource ID for wanted posts
    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_post_wanted;
    }
    // Setup specific views for wanted post activity
    @Override
    protected void setupSpecificViews() {
    }
    // Validate inputs for wanted post form
    @Override
    protected boolean validateInputs() {
        boolean valid = true;
        int selectedId = radioGroup.getCheckedRadioButtonId();
        // Validation for radio group and quantity input
        if (selectedId == -1) {
            valid = false;
            Toast.makeText(this, "Please enter a quantity or select a button", Toast.LENGTH_SHORT).show();
        }else if(selectedId == R.id.radioButtonOther){
            if (quantityEditText.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Please enter a quantity or select a button", Toast.LENGTH_SHORT).show();
                valid = false;
            }
        }
        // Validation for title and address
        if (titleEditText.getText().toString().trim().isEmpty()) {
            titleEditText.setError("Required");
            valid = false;
        }
        if (addressText.getText().toString().trim().isEmpty()) {
            addressText.setError("Required");
            valid = false;
        }
        return valid;
    }
}