package com.example.eatsy;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.HashMap;

public class ImportDataFromLocalJson {
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
            System.out.println("error");


            Gson gson = new Gson();
            Type type = new TypeToken<HashMap<String, Post>>(){}.getType();
            HashMap<String, Post> postMap = gson.fromJson(sb.toString(), type);
            return postMap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
