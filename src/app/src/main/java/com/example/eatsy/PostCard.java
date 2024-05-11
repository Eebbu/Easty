package com.example.eatsy;

import android.location.Address;
import android.location.Geocoder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

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
/**
 * Functionalities
 * 1) An activity to show selected post from dashboard.(Jinyang Zeng)
 * @author Jinyang Zeng(7727175)
 */

public class PostCard extends AppCompatActivity implements OnMapReadyCallback {

    private LatLng selectedLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_card);

        Post clickedPost = (Post) getIntent().getSerializableExtra("clickedPost");
        double latitude;
        double longitude;
        if (clickedPost.getLatitude()==null){
            latitude = -35.2809;
        }else {
            latitude = Double.parseDouble(clickedPost.getLatitude());
        }
        if (clickedPost.getLongitude()==null){
            longitude = 149.1300;
        }else {
            longitude = Double.parseDouble(clickedPost.getLongitude());
        }
        selectedLocation = new LatLng(latitude, longitude);



        ImageButton go_back = findViewById(R.id.leftArrowButton);
        go_back.setOnClickListener(v -> finish());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

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