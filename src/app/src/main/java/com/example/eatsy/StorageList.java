package com.example.eatsy;

import com.google.firebase.firestore.CollectionReference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StorageList {


    public static List<Post> postList = new ArrayList<>();

    public static Map<String,Post> mapList = new HashMap<>();

    public static void initPostData(){
        List<Post> resList = new ArrayList<>();
        CollectionReference postsCollectionRef = FireStoreHelper.getCollectionRef("posts");
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
                    Post post = new Post(id,userID, userName, postType, postTitle, postDescription, quantity, pickUpTimes, latitude, longitude, images);
                    resList.add(post);
                    mapList.put(id,post);
                });
                postList = resList;
            }
        });
    }


    public static void initLocalData(){
        convertDataId();
        Collection<Post> values = mapList.values();
        postList = new ArrayList<>(values);
    }

    public static void convertDataId(){
        for (String s : mapList.keySet()) {
            Post post = mapList.get(s);
            if(post!=null){
                post.setId(s);
            }
        }
    }
}
