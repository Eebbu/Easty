package com.example.eatsy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;
/**
 * PostDetailActivity is an Android activity that displays detailed information about a specific post.
 * It retrieves data passed through intents, loads images using Picasso, and sets up views to show
 * various attributes of the post such as title, user information, pick-up times, and location.
 *Author: Lin Xi(u7777752)
 */
public class PostDetailActivity extends AppCompatActivity {

    /**
     * Called when the activity is starting. This is where most initialization should go:
     * calling setContentView(int) to inflate the activity's UI, using findViewById(int) to programmatically
     * interact with widgets in the UI, setting up listeners, and getting data from intents.
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     *                           this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     *                           Otherwise, it is null.
     */

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
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

            // Display pick-up times.
            TextView timeView = findViewById(R.id.postTime);
            timeView.setText(post.getPickUpTimes());
            // Display meeting address composed of latitude and longitude.
            TextView addressView = findViewById(R.id.meetingAddress);
            addressView.setText("Address："+post.getLatitude()+","+post.getLongitude());
            // Display the post description.
            TextView descView = findViewById(R.id.description);
            descView.setText(post.getPostDescription());
        }
    }
}
