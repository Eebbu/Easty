package com.example.eatsy;

import java.util.HashMap;

public interface DataDownloadCallback<T>{
    void onDataReceived(HashMap<String, T> data);
    void onDataDownloadFailed(Exception exception);
}
