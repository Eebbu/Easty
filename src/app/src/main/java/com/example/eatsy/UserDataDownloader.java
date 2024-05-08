package com.example.eatsy;

import android.database.Observable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListUtil;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The UserDataDownloader class handles the downloading of user data from a Firestore collection.
 * This class uses asynchronous operations to fetch data and notifies the caller via a callback interface.
 * Attempts to fetch data from local cache if online fetching fails or if the device is offline.
 */
public class UserDataDownloader {

    /**
     * Downloads user data from a specified Firestore collection and stores it in a HashMap.
     * Notifies the callback upon successful data retrieval or in case of a failure.
     */

    public CompletableFuture<ConcurrentHashMap<String, userFT>> downloadData(CollectionReference usersCollectionRef) {
        CompletableFuture<ConcurrentHashMap<String, userFT>> future = new CompletableFuture<>();

        usersCollectionRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    ConcurrentHashMap<String, userFT> userHashMap = new ConcurrentHashMap<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String email = document.getId();
                        String username = document.getString("name");
                        String photoURL = document.getString("photo_url");
                        ArrayList<String> postid = (ArrayList<String>) document.get("postid");
                        userFT user = new userFT(username, email, email, photoURL, postid);
                        userHashMap.put(email, user);
                    }
                    future.complete(userHashMap);
                } else {
                    // Attempt to retrieve data from cache if network call fails
                    usersCollectionRef.get().addOnCompleteListener(taskFromCache -> {
                        if (taskFromCache.isSuccessful()) {
                            ConcurrentHashMap<String, userFT> userHashMap = new ConcurrentHashMap<>();
                            for (QueryDocumentSnapshot document : taskFromCache.getResult()) {
                                String email = document.getId();
                                String username = document.getString("name");
                                String photoURL = document.getString("photo_url");
                                ArrayList<String> postid = (ArrayList<String>) document.get("postid");
                                userFT user = new userFT(username, email, email, photoURL, postid);
                                userHashMap.put(email, user);
                            }
                            future.complete(userHashMap);
                        } else {
                            future.completeExceptionally(task.getException());
                        }
                    });
                }
            }
        });

        return future;
    }
}

