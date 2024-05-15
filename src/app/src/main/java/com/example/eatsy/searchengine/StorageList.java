package com.example.eatsy.searchengine;

import com.example.eatsy.Post;
import com.example.eatsy.datamanagement.FireStoreHelper;
import com.google.firebase.firestore.CollectionReference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is for loading data
 *     One is loading data from our real-time firebase.
 *     This is for formal presentation
 *     One is loading data from local json.
 *     This is for testing.
 *     Author:Lin Xi(u7777752)
 */
public class StorageList {


    public static List<Post> postList = new ArrayList<>();

    public static Map<String,Post> mapList = new HashMap<>();

    public static AVLTree avlTree = new AVLTree();

    /**
     Upload data from realtime-firebase
     This is for formal presentation
     Author:Lin Xi(u7777752)
     **/
    public static void initPostData(){
        List<Post> resList = new ArrayList<>();
        CollectionReference postsCollectionRef = FireStoreHelper.getCollectionRef("posts");
        postsCollectionRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                task.getResult().forEach(document -> {
                    String id = document.getId();
                    String userID = document.getString("userID");
                    String userName = document.getString("username");
                    String postType = document.getString("postType");
                    String postTitle = document.getString("postTitle");
                    String postDescription = document.getString("postDescription");
                    String quantity = document.getString("quantity");
                    String pickUpTimes = document.getString("pickUpTimes");
                    String latitude = document.getString("latitude");
                    String longitude = document.getString("longitude");
                    ArrayList<String> images = (ArrayList<String>) document.get("images");
                    Post post = new Post(userID, userName, postType, postTitle, postDescription, quantity, pickUpTimes, latitude, longitude, images);
                    resList.add(post);
                    mapList.put(id,post);
                });
                postList = resList;
            }
        });
    }

    /**
     * Upload data from local json.
     * This is for testing.
     * Author:Lin Xi(u7777752)
     **/
    public static void initLocalData(){
        convertDataId();
        Collection<Post> values = mapList.values();
        postList = new ArrayList<>(values);
        avlTree.buildTree(postList.toArray());
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
