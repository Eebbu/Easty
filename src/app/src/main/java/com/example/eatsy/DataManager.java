package com.example.eatsy;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class implements a singleton pattern for
 * managing and storing the mapping relationship
 * between user (userFT) and post (Post) data
 * Author: Boxuan Lin(u7705128), Lin Xi(u7777752) and Jinyang Zeng(u7727175)
 */
public class DataManager {
    private static final DataManager instance = new DataManager();
    private ConcurrentHashMap<String, userFT> userHashMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, Post> postHashMap = new ConcurrentHashMap<>();

    //Make sure it has only one instance through a private constructor
    private DataManager() {

    }

    public static DataManager getDataInstance() {
        return instance;
    }

    public void setUserHashMap(ConcurrentHashMap<String, userFT> hashMap) {
        this.userHashMap = hashMap;
    }

    public ConcurrentHashMap<String, userFT> getUserHashMap() {
        return userHashMap;
    }

    public void setPostHashMap(ConcurrentHashMap<String, Post> hashMap) {
        this.postHashMap = hashMap;
    }

    public ConcurrentHashMap<String, Post> getPostHashMap() {
        return postHashMap;
    }

    /**
     * Generate unique ID based on timestamp
     *  @return Returns a unique ID based on timestamp and random number
     */
    public static String generateTimestampBasedId() {
        long timestamp = System.currentTimeMillis(); // Get current timestamp
        int randomNum = ThreadLocalRandom.current().nextInt(100, 1000); // Generate a random number between 100 and 999
        return timestamp + "" + randomNum; // Concatenate timestamp and random number into string
    }

    public String searchUserName(String userInfo){
        return userHashMap.get(userInfo).getUsername();

    }
}
