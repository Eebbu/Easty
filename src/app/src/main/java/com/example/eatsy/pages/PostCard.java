package com.example.eatsy.pages;

import android.location.Address;
import android.location.Geocoder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.eatsy.Post;
import com.example.eatsy.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * Functionalities
 * 1) An activity to show post deatails from dashboard, search page.(Jinyang Zeng)
 * @author Jinyang Zeng(7727175)
 */
public class PostCard extends AppCompatActivity implements OnMapReadyCallback {
    private LatLng selectedLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_card);

        //go back.
        ImageButton go_back = findViewById(R.id.leftArrowButton);
        go_back.setOnClickListener(v -> finish());


        Post clickedPost = (Post) getIntent().getSerializableExtra("clickedPost");
        double latitude;
        double longitude;
        if (clickedPost.getLatitude()==null){
            //default value center of Canberra;
            latitude = -35.2809;
        }else {
            latitude = Double.parseDouble(clickedPost.getLatitude());
        }
        if (clickedPost.getLongitude()==null){
            //default value center of Canberra;
            longitude = 149.1300;
        }else {
            longitude = Double.parseDouble(clickedPost.getLongitude());
        }
        selectedLocation = new LatLng(latitude, longitude);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //set page appearance
        TextView banner_title = findViewById(R.id.banner_title);
        banner_title.setText(clickedPost.getUserName());

        TextView title = findViewById(R.id.title);
        title.setText(clickedPost.getPostTitle());

        TextView description = findViewById(R.id.description);
        description.setText(clickedPost.getPostDescription());

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses;
        String addressLine = null;
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (addresses != null && !addresses.isEmpty()) {
                Address address = addresses.get(0);
                addressLine = address.getAddressLine(0); // Get address
                // Display address in the related map
        }

        TextView wantInExchange = findViewById(R.id.want_in_exchange);
        if (clickedPost.getWantInExchange() != null && !Objects.equals(clickedPost.getWantInExchange(), "")){
            wantInExchange.setText("I want " + clickedPost.getWantInExchange());
            wantInExchange.setVisibility(View.VISIBLE);
        }else{
            wantInExchange.setVisibility(View.GONE);
        }


        TextView remainAndMeetTime = findViewById(R.id.remainAndMeetTime);
        remainAndMeetTime.setText(clickedPost.getQuantity() + " remain and meet at");

        TextView meetingAddress = findViewById(R.id.meetingAddress);
        meetingAddress.setText(addressLine);

        ImageButton food_picture = findViewById(R.id.food_picture);
        if (clickedPost.getImages() != null && clickedPost.getImages().size() > 0){
            Picasso.get()
                    .load(clickedPost.getImages().get(0))
                    .placeholder(R.drawable.beans)
                    .into(food_picture);
        }else{
            Picasso.get()
                    .load(R.drawable.foodwant)
                    .into(food_picture);
        }


    }

    public void onMapReady(@NonNull GoogleMap googleMap) {

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(selectedLocation, 11));
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setScrollGesturesEnabled(false);
        googleMap.getUiSettings().setRotateGesturesEnabled(false);
        googleMap.getUiSettings().setTiltGesturesEnabled(false);
        googleMap.addMarker(new MarkerOptions().position(selectedLocation).title("Selected Location"));

    }

}