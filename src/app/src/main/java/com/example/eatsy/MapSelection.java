package com.example.eatsy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class MapSelection extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double selectedLatitude;
    private double selectedLongitude;
    private static final LatLng CANBERRA = new LatLng(-35.282001, 149.128998);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_selection);

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

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(CANBERRA, 13));
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
                selectedLatitude = latLng.latitude;
                selectedLongitude = latLng.longitude;
                getAddress(selectedLatitude,selectedLongitude);
            }
        });
    }

    private void getAddress(double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 5);
            if (addresses != null && addresses.size() > 0) {
                Set<String> addressSet = new HashSet<>();
                for (Address addr : addresses) {
                    addressSet.add(addr.getAddressLine(0)); // 添加地址信息到Set
                }

                List<String> addressList = new ArrayList<>(addressSet);

                ListView listView = findViewById(R.id.listView);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, addressList);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent resultIntent = new Intent();
                        double[] result = {selectedLatitude, selectedLongitude};
                        resultIntent.putExtra("selectedPoint", result);
                        resultIntent.putExtra("selectedAddress",addressList.get(position));
                        setResult(RESULT_OK, resultIntent);
                        finish();
                    }
                });

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}