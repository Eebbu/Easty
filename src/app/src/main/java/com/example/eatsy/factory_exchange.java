package com.example.eatsy;

import android.net.Uri;

import com.google.firebase.storage.StorageReference;

/**
 * @author Zihan Yuan(u7773880)
 */
public class factory_exchange extends Post{
    private Uri filePath;// URI for the image file
//    private StorageReference storageReference;// Reference to Firebase Storage
    // Constructor for factory_exchange
    factory_exchange(String userName,String postTitle,String postDescription,String wantInExchange,String quantity,
                     String pickUpTimes,String latitude,String longitude, String image,Uri filePath,
                     StorageReference storageReference){
        // Call to the superclass (Post) constructor
        super(userName,"exchange",postTitle,postDescription,quantity,latitude,longitude);
        this.addImages(image);
        this.setPickUpTimes(pickUpTimes);
        this.setWantInExchange(wantInExchange);
        this.setFilePath(filePath);// Set the file path for the image
        this.setStorageReference(storageReference);// Set the storage reference for Firebase
    }
}
