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
        setContentView(R.layout.activity_post_detail);
        ImageButton go_back = findViewById(R.id.leftArrowButton);
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Search.class);
                startActivity(intent);
                finish();
            }
        });
        Intent intent = getIntent();
        if (intent != null) {
            int id = intent.getIntExtra("postId",0);
            // get post
            Post post = StorageList.mapList.get(String.valueOf(id));

            //picture
            ImageButton foodIdTextView = findViewById(R.id.food_picture);
            if(post.getImages()!=null && !post.getImages().isEmpty()){
                String imagePath = post.getImages().get(0);
                Picasso.get().load(imagePath).into(foodIdTextView);
            }else{
                Picasso.get().load("@drawable/fruit").into(foodIdTextView);
            }

            //value
            TextView titleView = findViewById(R.id.title);
            titleView.setText(post.getPostTitle());


            TextView quantityView = findViewById(R.id.banner_title);
            quantityView.setText(post.getUserName());

            TextView userIdView = findViewById(R.id.remainAndMeetTime);
            userIdView.setText("UserID："+post.getUserID()+"UserName："+post.getUserName());


            TextView timeView = findViewById(R.id.postTime);
            timeView.setText(post.getPickUpTimes());

            TextView addressView = findViewById(R.id.meetingAddress);
            addressView.setText("Address："+post.getLatitude()+","+post.getLongitude());

            TextView descView = findViewById(R.id.description);
            descView.setText(post.getPostDescription());
        }
    }
}
