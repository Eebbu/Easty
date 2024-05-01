package com.example.eatsy;

import android.net.Uri;

import com.google.firebase.storage.StorageReference;

import java.util.UUID;

public class factory_donate extends Post{
    private Uri filePath;
    private StorageReference storageReference;

    factory_donate(String userName,String postTitle,String postDescription, String quantity,
                   String pickUpTimes,String latitude,String longitude, String image,Uri filePath,
                   StorageReference storageReference){
        super(userName,"donate",postTitle,postDescription,quantity,latitude,longitude);
        this.addImages(image);
        this.setPickUpTimes(pickUpTimes);
        this.filePath = filePath;
        this.storageReference = storageReference;
    }


    @Override
    public void saveToFirebase() {
        //.....
        if (filePath != null) {
            storageReference.putFile(filePath);//save image to firebase
        }
    }
}
