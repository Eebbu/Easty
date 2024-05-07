package com.example.eatsy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.squareup.picasso.Picasso;

import java.util.List;
/**
 * Functionbilities
 * 1) UI design(Vishakha Mathur)
 * 2) link the page to profile(Vishakha Mathur)
 * 3) link page to search view and addview(Zihan Yuan)
 * 4) adapter to show poster(Jinyang Zeng)
 * @author Vishakha Mathur(u7663368) Zihan Yuan(u7773880) Jinyang Zeng(7727175)
 */
public class DashboardActivity extends AppCompatActivity {

//    ConcurrentHashMap<String, Post> posts;
    HashMap<String, Post> posts;
    ArrayList<Post> postsToShow = new ArrayList<>();
    private RecyclerView recyclerView;
    private PostAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

//        posts = DataManager.getDataInstance().getPostHashMap();
        posts = ImportDataFromLocalJson.read(getApplicationContext());

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        for (int i = posts.size()-1; i >0 ; i--) {
            postsToShow.add(posts.get(i+""));
        }


        adapter = new PostAdapter(this, postsToShow);
        recyclerView.setAdapter(adapter);
        ImageView searchButton = findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), search.class);
                startActivity(intent);
//                finish();
            }
        });

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        LinearLayout addPageTextView = findViewById(R.id.add_pg);
        addPageTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this,Addpage.class);
                startActivity(intent);
            }
        });




        ImageView person = findViewById(R.id.profile);
        person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        adapter.setOnItemClickListener(new PostAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Post clickedPost = postsToShow.get(position);
                Intent intent = new Intent(DashboardActivity.this, postCard.class);
                intent.putExtra("clickedPost",clickedPost);
                startActivity(intent);
            }
        });
    }




}