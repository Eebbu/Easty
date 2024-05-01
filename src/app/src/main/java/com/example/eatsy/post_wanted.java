package com.example.eatsy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.storage.StorageReference;

import java.util.UUID;

public class post_wanted extends post_base {

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_post_wanted;
    }
    @Override
    protected void setupSpecificViews() {
    }
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

        if (addressEditText.getText().toString().trim().isEmpty()) {
            addressEditText.setError("Required");
            valid = false;
        }
        return valid;
    }
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
        //TODO revise these four string
//        String userName = "someUserName";
        String userName = getIntent().getStringExtra("USERNAME");
        String latitude = "111";
        String longtitude = "222";
        factory_wanted post = new factory_wanted(userName, title, description, quantity, latitude, longtitude);
        post.saveToFirebase();
    }
}