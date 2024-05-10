package com.example.eatsy;
/**
 * @author Zihan Yuan(u7773880)
 * @author Boxuan Lin(u7705128) for saveToFirebase()
 */
public class factory_wanted extends Post{
    factory_wanted(String userName,String postTitle,String postDescription, String quantity,String latitude,String longitude){
        super(userName,"wanted",postTitle,postDescription,quantity,latitude,longitude);
    }
}
