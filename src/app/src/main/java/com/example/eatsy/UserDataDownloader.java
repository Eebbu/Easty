package com.example.eatsy;



import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The UserDataDownloader class handles the downloading of user data from a FireStore collection.
 * This class uses asynchronous operations to fetch data and notifies the caller via a callback interface.
 * Attempts to fetch data from local cache if online fetching fails or if the device is offline.
 */
public class UserDataDownloader {
    /**
     * Downloads user data from a specified FireStore collection and stores it in a ConcurrentHashMap.
     * Notifies the callback upon successful data retrieval or in case of a failure.
     */

    public CompletableFuture<ConcurrentHashMap<String, userFT>> downloadData(CollectionReference usersCollectionRef) {
        CompletableFuture<ConcurrentHashMap<String, userFT>> future = new CompletableFuture<>();

        usersCollectionRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                ConcurrentHashMap<String, userFT> userHashMap = new ConcurrentHashMap<>();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    String email = document.getId();
                    String username = document.getString("name");
                    String photoURL = document.getString("photo_url");
                    ArrayList<String> postId = (ArrayList<String>) document.get("postid");
                    userFT user = new userFT(username, email, email, photoURL, postId);
                    userHashMap.put(email, user);
                }
                future.complete(userHashMap);
            } else {
                // FireStore will automatically attempt to use cache if network request fails
                future.completeExceptionally(task.getException());
            }
        });

        return future;
    }
}


