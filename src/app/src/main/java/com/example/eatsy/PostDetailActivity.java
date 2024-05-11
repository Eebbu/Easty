package com.example.eatsy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class PostDetailActivity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ImageButton go_back = findViewById(R.id.leftArrowButton);
        go_back.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Search.class);
            startActivity(intent);
            finish();
        });
        Intent intent = getIntent();
        if (intent != null) {
            int id = intent.getIntExtra("postId",0);
            // get post
            Post post = StorageList.mapList.get(String.valueOf(id));

            //picture
            ImageButton foodIdTextView = findViewById(R.id.food_img);
            if(post.getImages()!=null && !post.getImages().isEmpty()){
                String imagePath = post.getImages().get(0);
                Picasso.get().load(imagePath).into(foodIdTextView);
            }else{
                Picasso.get().load("@drawable/fruit").into(foodIdTextView);
            }

            //value
            TextView titleView = findViewById(R.id.food_title);
            titleView.setText(post.getPostTitle());


            TextView typeView = findViewById(R.id.type_value);
            typeView.setText(post.getPostType());

            TextView quantityView = findViewById(R.id.quantity_value);
            quantityView.setText(post.getQuantity());

            TextView userIdView = findViewById(R.id.user_id_value);
            userIdView.setText(post.getUserID());


            TextView userNameView = findViewById(R.id.user_name_value);
            userNameView.setText(post.getUserName());

            TextView timeView = findViewById(R.id.time_id_value);
            timeView.setText(post.getPickUpTimes());

            TextView addressView = findViewById(R.id.address_id_value);
            addressView.setText(post.getLatitude()+","+post.getLongitude());

            TextView descView = findViewById(R.id.description_value);
            descView.setText(post.getPostDescription());
        }
    }
}
