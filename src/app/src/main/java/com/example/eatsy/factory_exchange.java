package com.example.eatsy;

import android.net.Uri;

import com.google.firebase.storage.StorageReference;

public class factory_exchange extends Post{
    private Uri filePath;
    private StorageReference storageReference;
    String WantInExchange;
    factory_exchange(String userName,String postTitle,String postDescription,String wantInExchange,String quantity,
                     String pickUpTimes,String latitude,String longitude, String image,Uri filePath,
                     StorageReference storageReference){
        super(userName,"exchange",postTitle,postDescription,quantity,latitude,longitude);
        this.addImages(image);
        this.setPickUpTimes(pickUpTimes);
        this.WantInExchange = wantInExchange;
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
