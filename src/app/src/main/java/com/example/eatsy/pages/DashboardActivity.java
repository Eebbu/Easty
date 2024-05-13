package com.example.eatsy.pages;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eatsy.datamanagement.LocalJsonDataBase;
import com.example.eatsy.Post;
import com.example.eatsy.PostAdapter;
import com.example.eatsy.R;
import com.example.eatsy.Search;
import com.example.eatsy.userFT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;


/**
 * Functionalities
 * 1) UI design(Vishakha Mathur)
 * 2) link the page to profile(Vishakha Mathur)
 * 3) link the page to search view and addView(Zihan Yuan)
 * 4) recycle view to show poster(Jinyang Zeng)
 * 5) link the page to detailed post page(Jinyang Zeng)
 * 6) function to simulate the DataStream(Jinyang Zeng)
 * @author Vishakha Mathur(u7663368) Zihan Yuan(u7773880) Jinyang Zeng(7727175)
 */
public class DashboardActivity extends AppCompatActivity {

    public static HashMap<String, Post> posts;
    public static HashMap<String, userFT> users;
    public static ArrayList<Post> postsToShow;
    private static Handler handler;
    private static Runnable runnable;

    public static PostAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        //initialize the data.
        if (posts == null){
            posts = LocalJsonDataBase.getPosts(getApplicationContext());
            users = LocalJsonDataBase.getUsers(getApplicationContext());
        }
        //decide which posts will show in the dashboard page.
        ArrayList<String> indexInStrings = new ArrayList<>(posts.keySet());
        //sort in dictionary order 10 will in front of 2;
        Collections.sort(indexInStrings);
        if (postsToShow == null){
            postsToShow = new ArrayList<>();
            for (int i = 0; i < posts.size()/10  ; i++) {
                postsToShow.add(posts.get(indexInStrings.get(i)));
            }
        }

        //set the recycleView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new PostAdapter(postsToShow);
        recyclerView.setAdapter(adapter);
        simulateDataStream(posts);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        //search function
        ImageView searchButton = findViewById(R.id.search_button);
        searchButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Search.class);
            startActivity(intent);
        });

        //addPage function
        LinearLayout addPageTextView = findViewById(R.id.add_pg);
        addPageTextView.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, AddPage.class);
            startActivity(intent);
        });

        //profile function
        ImageView person = findViewById(R.id.profile);
        person.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
            startActivity(intent);
        });

        //lead to detail post.
        adapter.setOnItemClickListener(position -> {
            Post clickedPost = postsToShow.get(position);
            Intent intent = new Intent(DashboardActivity.this, PostCard.class);
            intent.putExtra("clickedPost",clickedPost);
            startActivity(intent);
        });
    }

    //simulate dataStream. The post update.
    private void simulateDataStream(HashMap<String, Post> posts){
        if(handler == null){
            handler = new Handler();
        }
        if(runnable == null){
            runnable = new Runnable() {
                @Override
                public void run() {
                    //select random post(we have 2000 posts with picture, for better pre, only choose those with image.)
                    String randomKey = String.valueOf(new Random().nextInt(2000) + 1);
                    Post post = posts.get(randomKey);

                    postsToShow.add(0, post);
                    adapter.notifyDataSetChanged();
                    showToast();

                    //random the fresh time to simulate the post update.
                    int nextRefreshTime = new Random().nextInt(5) + 10;
                    handler.postDelayed(this, nextRefreshTime * 1000);
                }
            };
            handler.postDelayed(runnable, 5000);
        }
    }

    private void showToast() {
        Toast.makeText(this, "new post", Toast.LENGTH_SHORT).show();
    }

}