package com.example.eatsy;

import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class postCard extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final LatLng CANBERRA = new LatLng(-35.282001, 149.128998);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_card);


        ImageButton go_back = findViewById(R.id.leftArrowButton);
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(CANBERRA, 11));
        mMap.getUiSettings().setZoomGesturesEnabled(false);
        mMap.getUiSettings().setZoomControlsEnabled(true);

        // 设置地图点击事件监听器
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                // 在点击的位置添加标记
                mMap.clear(); // 清除旧标记
                mMap.addMarker(new MarkerOptions().position(latLng).title("Selected Location"));
                // 可以在这里获取选点的经纬度和地址信息，以便将其提交到您的“post”中
                double latitude = latLng.latitude;
                double longitude = latLng.longitude;
            }
        });
    }

}