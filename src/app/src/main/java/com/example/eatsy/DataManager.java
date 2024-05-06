package com.example.eatsy;

import java.util.concurrent.ConcurrentHashMap;

public class DataManager {
    private static final DataManager instance = new DataManager();
    private ConcurrentHashMap<String, userFT> userHashMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, Post> postHashMap = new ConcurrentHashMap<>();

    private DataManager() {}

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
}
