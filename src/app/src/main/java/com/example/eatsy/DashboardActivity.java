package com.example.eatsy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


/**
 * Functionalities
 * 1) UI design(Vishakha Mathur)
 * 2) link the page to profile(Vishakha Mathur)
 * 3) link page to search view and addView(Zihan Yuan)
 * 4) recycle view to show poster(Jinyang Zeng)
 * 5） link page to detailed post page(Jinyang Zeng)
 * @author Vishakha Mathur(u7663368) Zihan Yuan(u7773880) Jinyang Zeng(7727175)
 */
public class DashboardActivity extends AppCompatActivity {

    static HashMap<String, Post> posts;
    static HashMap<String, userFT> users;
    static ArrayList<Post> postsToShow = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);



        if (posts == null){
            posts = ImportDataFromLocalJson.read(getApplicationContext());
        }
        if (users == null){
            users = ImportDataFromLocalJson.readUser(getApplicationContext());
        }


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<String> indexInStrings = new ArrayList<>(posts.keySet());
        Collections.sort(indexInStrings);
        if (postsToShow.size() == 0){
            for (int i = 0; i < posts.size()  ; i++) {
                postsToShow.add(posts.get(indexInStrings.get(i)));
            }
        }

        System.out.println(postsToShow.size());

        PostAdapter adapter = new PostAdapter(postsToShow);
        recyclerView.setAdapter(adapter);
        ImageView searchButton = findViewById(R.id.search_button);
        searchButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Search.class);
            startActivity(intent);
//                finish();
        });

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        LinearLayout addPageTextView = findViewById(R.id.add_pg);
        addPageTextView.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this,AddPage.class);
            startActivity(intent);
        });



        ImageView person = findViewById(R.id.profile);
        person.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
            startActivity(intent);
        });

        adapter.setOnItemClickListener(position -> {
            Post clickedPost = postsToShow.get(position);
            Intent intent = new Intent(DashboardActivity.this, PostCard.class);
            intent.putExtra("clickedPost",clickedPost);
            startActivity(intent);
        });
    }

}