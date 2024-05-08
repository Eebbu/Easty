package com.example.eatsy;

import android.net.Uri;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
/**
 * @author Zihan Yuan(u7773880) Boxuan Lin(u7705128)
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
}


