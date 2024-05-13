package com.example.eatsy.datamanagement;

import android.content.Context;

import com.example.eatsy.Post;
import com.example.eatsy.datamanagement.DataDownloader;
import com.example.eatsy.userFT;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;

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
            Post post = new Post(userID, userName, postType, postTitle, postDescription, quantity, pickUpTimes, latitude, longitude, images);
            postHashMap.put(postID, post);
        });
    }
}



