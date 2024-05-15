package com.example.eatsy.datamanagement;

import android.content.Context;

import com.example.eatsy.Post;
import com.example.eatsy.datamanagement.DataDownloader;
import com.example.eatsy.userFT;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
/**
 * @author : Boxuan Lin(u7705128)
 */
public class PostDataDownloader extends DataDownloader<Post> {
    /**
     * Downloads post data from a specified FireStore collection and stores it in a ConcurrentHashMap.
     * Utilizes FireStore's automatic cache management to handle offline data loading when necessary.
     */
    public PostDataDownloader(Context context) {
        super(context, Post.class);
    }
    @Override
    protected void populateHashMapFromQuerySnapshot(QuerySnapshot snapshot, HashMap<String, Post> postHashMap) {
        snapshot.forEach(document -> {
            String postID = document.getId();
            String userID = document.contains("userID") ? document.getString("userID") : "";
            String userName = document.contains("userName") ? document.getString("userName") : "";
            String postType = document.contains("postType") ? document.getString("postType") : "";
            String postTitle = document.contains("postTitle") ? document.getString("postTitle") : "";
            String postDescription = document.contains("postDescription") ? document.getString("postDescription") : "";
            String quantity = document.contains("quantity") ? document.getString("quantity") : "";
            String pickUpTimes = document.contains("pickUpTimes") ? document.getString("pickUpTimes") : "";
            String latitude = document.contains("latitude") ? document.getString("latitude") : "";
            String longitude = document.contains("longitude") ? document.getString("longitude") : "";
            ArrayList<String> images = document.contains("images") ? (ArrayList<String>) document.get("images") : new ArrayList<>();

            Post post = new Post(postID, userID, userName, postType, postTitle, postDescription, quantity, pickUpTimes, latitude, longitude, images);
            postHashMap.put(postID, post);
        });
    }
}



