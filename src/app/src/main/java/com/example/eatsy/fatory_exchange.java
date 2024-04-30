package com.example.eatsy;

public class fatory_exchange extends Post{
    fatory_exchange(String userName,String postTitle,String postDescription, String wantInExchange,String quantity, String pickUpTimes,String latitude,String longitude, String image){
        super(userName,"exchange",postTitle,postDescription,quantity,latitude,longitude);
        this.addImages(image);
        this.setPickUpTimes(pickUpTimes);
        this.setWantInExchange(wantInExchange);
    }
    @Override
    public void saveToFirebase() {
        //.....
    }
}
