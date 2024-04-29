package com.example.eatsy;

import java.util.HashMap;
/**
 * The DataDownloadCallback interface provides a mechanism to handle data retrieval operations asynchronously.
 * It is designed to be used where data needs to be fetched from a remote source, such as a server or database.
 *
 * @param <T> The type of data that is expected to be received. This allows the interface to be used generically with any type of data.
 */
public interface DataDownloadCallback<T>{
    /**
     * Called when data is successfully received. This method provides the received data as a HashMap.
     *
     * @param data A HashMap containing the data retrieved, where the keys are String identifiers,
     *             and the values are of type T, representing the data items.
     */
    void onDataReceived(HashMap<String, T> data);
    /**
     * Called when there is a failure in downloading the data. This method provides the exception caught during the data retrieval process.
     *
     * @param exception An Exception instance that was thrown during the data download process, providing details of what went wrong.
     */

    void onDataDownloadFailed(Exception exception);

}