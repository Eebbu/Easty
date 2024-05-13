package com.example.eatsy.datamanagement;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public abstract class DataDownloader<T> {
    /**
     * Abstract method to download data from Firestore and populate a ConcurrentHashMap.
     * Specific data handling is implemented in derived classes.
     */
    public CompletableFuture<ConcurrentHashMap<String, T>> downloadData(CollectionReference collectionRef) {
        CompletableFuture<ConcurrentHashMap<String, T>> future = new CompletableFuture<>();

        collectionRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                ConcurrentHashMap<String, T> dataMap = new ConcurrentHashMap<>();
                populateHashMapFromQuerySnapshot(task.getResult(), dataMap);
                future.complete(dataMap);
            } else {
                future.completeExceptionally(task.getException());
            }
        });

        return future;
    }

    protected abstract void populateHashMapFromQuerySnapshot(QuerySnapshot snapshot, ConcurrentHashMap<String, T> map);
}
