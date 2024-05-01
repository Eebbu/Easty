package com.example.eatsy;

import android.net.Uri;

import com.google.firebase.storage.StorageReference;

import java.util.UUID;

public class factory_donate extends Post{
    private Uri filePath;
    private StorageReference storageReference;
    factory_donate(String userName,String postTitle,String postDescription, String quantity, String pickUpTimes,String latitude,String longitude, String image){
        super(userName,"donate",postTitle,postDescription,quantity,latitude,longitude);
        this.addImages(image);
        this.setPickUpTimes(pickUpTimes);
    }

    public void setFilePath(Uri filePath) {
        this.filePath = filePath;
    }
    public void setStorageReference(StorageReference storageReference){
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
