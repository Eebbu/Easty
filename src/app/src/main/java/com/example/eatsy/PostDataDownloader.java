package com.example.eatsy;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CompletableFuture;
import java.util.ArrayList;

public class PostDataDownloader {
    /**
     * Downloads post data from a specified Firestore collection and stores it in a ConcurrentHashMap.
     * Attempts to fetch data from local cache if online fetching fails or if the device is offline.
     */
    public CompletableFuture<ConcurrentHashMap<String, Post>> downloadData(CollectionReference postsCollectionRef) {
        CompletableFuture<ConcurrentHashMap<String, Post>> future = new CompletableFuture<>();

        postsCollectionRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                ConcurrentHashMap<String, Post> postHashMap = new ConcurrentHashMap<>();
                populateHashMapFromQuerySnapshot(task.getResult(), postHashMap);
                future.complete(postHashMap);
            } else {
                // Attempt to load from local cache if the online fetch fails
                postsCollectionRef.get().addOnCompleteListener(taskFromCache -> {
                    if (taskFromCache.isSuccessful()) {
                        ConcurrentHashMap<String, Post> postHashMap = new ConcurrentHashMap<>();
                        populateHashMapFromQuerySnapshot(taskFromCache.getResult(), postHashMap);
                        future.complete(postHashMap);
                    } else {
                        future.completeExceptionally(task.getException());
                    }
                });
            }
        });

        return future;
    }

    private void populateHashMapFromQuerySnapshot(QuerySnapshot snapshot, ConcurrentHashMap<String, Post> postHashMap) {
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


