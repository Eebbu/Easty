package com.example.eatsy;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.io.Serializable;


/**
 * The Post class represents a post in our application.
 * This class includes details such as user identification, post type, and content descriptions,
 * which are essential for managing the posts in our APP.
 * @author Zihan Yuan(u7773880) Boxuan Lin(u7705128)
 */

public class Post implements Serializable{
    private static final long serialVersionUID = 1L;
    private String id;
    @SerializedName("userid")
    private String userID;
    @SerializedName("username")
    private String userName;
    @SerializedName("post_type")
    private String postType;

    @SerializedName("post_title")
    private String postTitle;
    @SerializedName("post_description")
    private String postDescription;

    private final String quantity;

    @SerializedName("pick_up_times")
    private String pickUpTimes;

    private final Address Address;

    private ArrayList<String> images;
    private String wantInExchange;
    private String filePath;

    public static class Address implements Serializable {
        @SerializedName("latitude")
        private String latitude;

        @SerializedName("longitude")
        private String longitude;

        public Address(String latitude,String longitude){
            this.latitude = latitude;
            this.longitude = longitude;
        }
    }



    public  Post(String userName,String postType,String postTitle,String postDescription, String quantity,String latitude,String longitude){
//
        this.userID = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        this.userName = userName;
        this.postType = postType;
        this.postTitle = postTitle;
        this.postDescription = postDescription;
        this.quantity = quantity;
        this.Address = new Address(latitude,longitude);
        this.wantInExchange = "";
        this.images = new ArrayList<>();
    }
    public Post(String id, String userID, String userName, String postType, String postTitle, String postDescription, String quantity, String pickUpTimes, String latitude, String longitude, ArrayList<String> images) {
        this.id = id;
        this.userID = userID;
        this.userName = userName;
        this.postType = postType;
        this.postTitle = postTitle;
        this.postDescription = postDescription;
        this.quantity = quantity;
        this.pickUpTimes = pickUpTimes;
        this.Address = new Address(latitude,longitude);
        this.images = new ArrayList<>(images);

    }
    public  Post(String userID,String userName,String postType,String postTitle,String postDescription, String quantity, String pickUpTimes,String latitude,String longitude,ArrayList<String> images){

        this.userID = userID;
        this.userName = userName;
        this.postType = postType;
        this.postTitle = postTitle;
        this.postDescription = postDescription;
        this.quantity = quantity;
        this.pickUpTimes = pickUpTimes;
        this.Address = new Address(latitude,longitude);
        if(images == null)
            this.images = new ArrayList<>();
        else
            this.images = new ArrayList<>(images);

    }


    // Getter methods for retrieving post information
    public String getId(){return id;}
    public String getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getPostType() {
        return postType;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getPickUpTimes() {
        return pickUpTimes;
    }
    public String getWantInExchange(){ return wantInExchange;}

    public String getLatitude() {
        return this.Address.latitude;
    }

    public String getLongitude() {
        return this.Address.longitude;
    }

    public ArrayList<String> getImages() {
        return new ArrayList<>(images);
    }

    public String getFilePath(){return filePath;}
    //public String getWantInExchange(String wantInExchange){return getWantInExchange;}

    public void setId(String id){this.id = id;}
    public void setPickUpTimes(String pickUpTimes) {
        this.pickUpTimes = pickUpTimes;
    }
    public void setWantInExchange(String wantInExchange){
        this.wantInExchange = wantInExchange;
    }
    public void setFilePath(String filePath){
        this.filePath = filePath;
    }

    public void addImages(String image) {
        if(images == null){
            images = new ArrayList<>();
            images.add(image);
        }else
            this.images.add(image);
    }

    public void setImages(String image) {
        images.set(0,image);
    }


    @NonNull
    @Override
    public String toString() {
        System.out.println("userid= " + userID);
        System.out.println("username= " + userName);
        System.out.println("post_type= " + postType);
        System.out.println("post_title= " + postTitle);
        System.out.println("post_description= " + postDescription);
        System.out.println("quantity= " + quantity);
        System.out.println("pick_up_times= " + pickUpTimes);
        System.out.println("latitude= " + Address.latitude);
        System.out.println("longitude= " + Address.longitude);
        if (images.size() == 0){
            System.out.println("images = ");
        }else{
            System.out.println("images= " + images.get(0));
        }
        System.out.println("wantInExchange= " + wantInExchange);
        return super.toString();
    }
}
