package com.example.eatsy;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

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

    /**
     * 生成基于时间戳的唯一 ID
     * @return 返回基于时间戳和随机数的唯一 ID
     */
    public static String generateTimestampBasedId() {
        long timestamp = System.currentTimeMillis(); // 获取当前时间戳
        int randomNum = ThreadLocalRandom.current().nextInt(100, 1000); // 生成一个100到999之间的随机数
        return timestamp + "" + randomNum; // 将时间戳和随机数拼接成字符串
    }

    public String searchUserName(String userInfo){
        return userHashMap.get(userInfo).getEmail();

    }
}
