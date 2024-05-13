package com.example.eatsy;

/**
 * @author Zihan Yuan(u7773880) Boxuan Lin(u7705128)
 */
public class Factory_donate extends Post {
    // Constructor for factory_donate
    public Factory_donate(String userName, String postTitle, String postDescription, String quantity,
                          String pickUpTimes, String latitude, String longitude, String image, String filePath){
        // Call to the superclass (Post) constructor
        super(userName,"donate",postTitle,postDescription,quantity,latitude,longitude);
        this.addImages(image);// Add image to the post
        this.setPickUpTimes(pickUpTimes); // Set pick-up times for the donation
        this.setFilePath(filePath);// Set the file path for the image
    }
}


