package com.example.eatsy;

import android.net.Uri;

import com.google.firebase.storage.StorageReference;

import java.util.UUID;
/**
 * @author Zihan Yuan(u7773880)
 */
public class factory_donate extends Post{
    // Constructor for factory_donate
    factory_donate(String userName,String postTitle,String postDescription, String quantity,
                   String pickUpTimes,String latitude,String longitude, String image,Uri filePath,
                   StorageReference storageReference){
        // Call to the superclass (Post) constructor
        super(userName,"donate",postTitle,postDescription,quantity,latitude,longitude);
        this.addImages(image);// Add image to the post
        this.setPickUpTimes(pickUpTimes); // Set pick-up times for the donation
        this.setFilePath(filePath);// Set the file path for the image
        this.setStorageReference(storageReference);// Set the storage reference for Firebase
    }


    @Override
    public void saveToFirebase() {
        //.....
        Uri filePath = this.getFilePath();
        StorageReference storageReference = getStorageReference();
        if (filePath != null) {
            storageReference.putFile(filePath);//save image to firebase
        }
        super.saveToFirebase();
    }
}
