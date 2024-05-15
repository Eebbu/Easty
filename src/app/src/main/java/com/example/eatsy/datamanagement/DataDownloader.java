package com.example.eatsy.datamanagement;

import android.content.Context;
import com.example.eatsy.Post;
import com.example.eatsy.userFT;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

/**
 * DataDownloader is an abstract class for downloading data from Firebase Firestore
 * and local JSON files.
 * @param <T> The type of data to download
 */
public abstract class DataDownloader<T> {
    public static boolean fileDownloaded = false;
    private Context context; // Context to access local resources
    private Class<T> typeParameterClass; // Class type for JSON deserialization

    /**
     * Constructor
     * @param context Context to access local resources
     * @param typeParameterClass The class type for JSON deserialization
     */

    public DataDownloader(Context context, Class<T> typeParameterClass) {
        this.context = context;
        this.typeParameterClass = typeParameterClass;
    }

    /**
     * Downloads data from the specified CollectionReference
     * @param collectionRef Firestore collection reference
     * @return A CompletableFuture containing a HashMap with the downloaded data
     */

    public CompletableFuture<HashMap<String, T>> downloadData(CollectionReference collectionRef) {
        CompletableFuture<HashMap<String, T>> future = new CompletableFuture<>();

        // Try to fetch from network
        collectionRef.get(Source.DEFAULT).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                HashMap<String, T> dataMap = new HashMap<>();
                populateHashMapFromQuerySnapshot(task.getResult(), dataMap);
                future.complete(dataMap);
            }  else {
                        // On cache fail, try to read from local JSON
                        readDataFromLocalJson(future);
                    }
                });


        fileDownloaded = true;


        return future;
    }

    /**
     * Abstract method to populate the HashMap from the QuerySnapshot
     * @param snapshot Firestore query snapshot
     * @param map HashMap to populate
     */

    protected abstract void populateHashMapFromQuerySnapshot(QuerySnapshot snapshot, HashMap<String, T> map);


    /**
     * Reads data from the local JSON file
     * @param future CompletableFuture to complete with the data
     */

    private void readDataFromLocalJson(CompletableFuture<HashMap<String, T>> future) {
        try {
            HashMap<String, T> dataMap = null;
            if (typeParameterClass == Post.class) {
                dataMap = (HashMap<String, T>) LocalJsonDataBase.read(context);
            } else if (typeParameterClass == userFT.class) {
                dataMap = (HashMap<String, T>) LocalJsonDataBase.readUser(context);
            }

            if (dataMap != null) {
                future.complete(dataMap);
            } else {
                throw new Exception("Failed to load data from local JSON");
            }
        } catch (Exception e) {
            future.completeExceptionally(e);
        }
    }
}
