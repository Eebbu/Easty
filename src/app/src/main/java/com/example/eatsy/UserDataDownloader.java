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
 */
public class UserDataDownloader {

    /**
     * Downloads user data from a specified Firestore collection and stores it in a HashMap.
     * Notifies the callback upon successful data retrieval or in case of a failure.
     */
    ConcurrentHashMap<String, userFT> userHashMap = new ConcurrentHashMap<>();
    public CompletableFuture<ConcurrentHashMap<String, userFT>> downloadData(CollectionReference usersCollectionRef) {
        CompletableFuture<ConcurrentHashMap<String, userFT>> future = new CompletableFuture<>();

        usersCollectionRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                task.getResult().forEach(document -> {
                    String email = document.getId();
                    String username = document.getString("name");
                    String photoURL = document.getString("photo_url");
                    ArrayList<String> postid = (ArrayList<String>) document.get("postid");
                    userFT user = new userFT(username, email,email, photoURL, postid);
                    userHashMap.put(email, user);
                });
                future.complete(userHashMap);
            } else {
                future.completeExceptionally(task.getException());
            }
        });

        return future;
    }


    public ConcurrentHashMap<String, userFT> getUserHashMap() {
        return userHashMap;

}
}
