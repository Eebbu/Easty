package com.example.eatsy;

import com.example.eatsy.Post;

/**
 * @author Zihan Yuan(u7773880)
 */
public class Factory_exchange extends Post {
    // Constructor for factory_exchange
    Factory_exchange(String userName, String postTitle, String postDescription, String wantInExchange, String quantity,
                     String pickUpTimes, String latitude, String longitude, String image, String filePath){
        // Call to the superclass (Post) constructor
        super(userName,"exchange",postTitle,postDescription,quantity,latitude,longitude);
        this.addImages(image);
        this.setPickUpTimes(pickUpTimes);
        this.setWantInExchange(wantInExchange);
        this.setFilePath(filePath);// Set the file path for the image
    }
}
