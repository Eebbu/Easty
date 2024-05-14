package com.example.eatsy.datamanagement;

import com.example.eatsy.Post;
import com.example.eatsy.userFT;

import java.util.HashMap;

import java.util.concurrent.ThreadLocalRandom;

/**
 * This class implements a singleton pattern for
 * managing and storing the mapping relationship
 * between user (userFT) and post (Post) data
 * Author: Boxuan Lin(u7705128), Lin Xi(u7777752) and Jinyang Zeng(u7727175)
 */
public class DataManager {
    private static final DataManager instance = new DataManager();
    private HashMap<String, userFT> userHashMap = new HashMap<>();
    private HashMap<String, Post> postHashMap = new HashMap<>();

    //Make sure it has only one instance through a private constructor
    private DataManager() {

    }

    public static DataManager getDataInstance() {
        return instance;
    }

    public void setUserHashMap(HashMap<String, userFT> hashMap) {
        this.userHashMap = hashMap;
    }

    public HashMap<String, userFT> getUserHashMap() {
        return userHashMap;
    }
    /**
     * Retrieves the post HashMap.
     * @return The post HashMap.
     */
    public void setPostHashMap(HashMap<String, Post> hashMap) {
        this.postHashMap = hashMap;
    }

    public HashMap<String, Post> getPostHashMap() {
        return postHashMap;
    }

    /**
     * Generate unique ID based on timestamp
     *  @return Returns a unique ID based on timestamp and random number
     */
    public static String generateTimestampBasedId() {
        long timestamp = System.currentTimeMillis(); // Get current timestamp
        int randomNum = ThreadLocalRandom.current().nextInt(100, 1000); // Generate a random number between 100 and 999
        return timestamp + String.valueOf(randomNum); // Concatenate timestamp and random number into string
    }

}
