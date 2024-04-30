package com.example.eatsy;

public class factory_donate extends Post{
    factory_donate(String userName,String postTitle,String postDescription, String quantity, String pickUpTimes,String latitude,String longitude, String image){
        super(userName,"donate",postTitle,postDescription,quantity,latitude,longitude);
        this.addImages(image);
        this.setPickUpTimes(pickUpTimes);
    }
    @Override
    public void saveToFirebase() {
        //.....
    }
}
