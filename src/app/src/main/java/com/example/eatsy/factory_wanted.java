package com.example.eatsy;

public class factory_wanted extends Post{
    factory_wanted(String userName,String postTitle,String postDescription, String quantity,String latitude,String longitude){
        super(userName,"wanted",postTitle,postDescription,quantity,latitude,longitude);
    }
    @Override
    public void saveToFirebase() {
        //.....
    }
}
