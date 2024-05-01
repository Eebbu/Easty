package com.example.eatsy;

import com.google.firebase.firestore.CollectionReference;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CompletableFuture;
import java.util.ArrayList;

public class PostDataDownloader {
    /**
     * Downloads post data from a specified Firestore collection and stores it in a ConcurrentHashMap.
     */
    ConcurrentHashMap<String, PostFT> postHashMap = new ConcurrentHashMap<>();

    public CompletableFuture<ConcurrentHashMap<String, PostFT>> downloadData(CollectionReference postsCollectionRef) {
        CompletableFuture<ConcurrentHashMap<String, PostFT>> future = new CompletableFuture<>();

        postsCollectionRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                postHashMap.clear();  // 清除旧的数据
                task.getResult().forEach(document -> {
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
                    PostFT post = new PostFT(userID, userName, postType, postTitle, postDescription, quantity, pickUpTimes, latitude, longitude, images);
                    postHashMap.put(postID, post);
                });
                future.complete(postHashMap);
            } else {
                future.completeExceptionally(task.getException());
            }
        });

        return future;
    }

    public ConcurrentHashMap<String, PostFT> getPostHashMap() {
        return postHashMap;
    }
}

