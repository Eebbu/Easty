package com.example.eatsy.datamanagement;

import android.content.Context;

import com.example.eatsy.Post;
import com.example.eatsy.R;
import com.example.eatsy.userFT;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.HashMap;
/**
 * Functionalities
 * 1) A function to load data when the database is offline.(Jinyang Zeng)
 * @author Jinyang Zeng(7727175)
 */
public class LocalJsonDataBase {
    public static HashMap<String, Post> read(Context context) {

        try {
            InputStream inputStream = context.getResources().openRawResource(R.raw.posts);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();


            Gson gson = new Gson();
            Type type = new TypeToken<HashMap<String, Post>>(){}.getType();
            return gson.fromJson(sb.toString(), type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static HashMap<String, userFT> readUser(Context context) {

        try {
            InputStream inputStream = context.getResources().openRawResource(R.raw.users_without_password);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();


            Gson gson = new Gson();
            Type type = new TypeToken<HashMap<String, userFT>>(){}.getType();
            return gson.fromJson(sb.toString(), type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
