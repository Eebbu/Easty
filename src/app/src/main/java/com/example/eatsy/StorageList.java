package com.example.eatsy;

import com.google.firebase.firestore.CollectionReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StorageList {


    public static List<PostFT> postList = new ArrayList<>();

    public static Map<String,PostFT> mapList = new HashMap<>();

    public static void initPostData(){
        List<PostFT> resList = new ArrayList<>();
        CollectionReference postsCollectionRef = FirestoreHelper.getCollectionRef("posts");
        postsCollectionRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                task.getResult().forEach(document -> {
                    String id = document.getId();
                    String userID = document.getString("userID");
                    String userName = document.getString("userName");
                    String postType = document.getString("postType");
                    String postTitle = document.getString("postTitle");
                    String postDescription = document.getString("postDescription");
                    String quantity = document.getString("quantity");
                    String pickUpTimes = document.getString("pickUpTimes");
                    String latitude = document.getString("latitude");
                    String longitude = document.getString("longitude");
                    ArrayList<String> images = (ArrayList<String>) document.get("images");
                    String food = document.getString("food");
                    PostFT post = new PostFT(id,userID, userName, postType, postTitle, postDescription, quantity, pickUpTimes, latitude, longitude, images,food);
                    resList.add(post);
                    mapList.put(id,post);
                });
                postList = resList;
            }
        });
    }
}
