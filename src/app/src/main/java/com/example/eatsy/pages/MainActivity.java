package com.example.eatsy.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.eatsy.MyApplication;
import com.example.eatsy.datamanagement.LocalJsonDataBase;
import com.example.eatsy.R;
import com.example.eatsy.searchengine.StorageList;


/** Functionality
 * This is the main page which appears when user opens the app.
 * It has title of the app and a login button that takes us to the login page.
 * Loading data from firebase
 * @author Vishakha Mathur(u7663368) and Lin Xi(u7777752)
 */
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button logIn = findViewById(R.id.start);


        logIn.setOnClickListener(v -> {
            logIn.setEnabled(false);
            // show toast for user
            Toast.makeText(this, "Data initialising", Toast.LENGTH_SHORT).show();

            // Thread to monitor the dataDownload
            new Thread(() -> {
                while (!MyApplication.fileDownloaded) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // turn to Dashboard.
                runOnUiThread(() -> {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                });
            }).start();
        });
    }

}