package com.example.eatsy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.*;


import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import java.io.IOException;
import java.util.*;

/**
 * Functionalities
 * 1) An activity to respond the request of location. Will provide geometric information.(Jinyang Zeng)
 * @author Jinyang Zeng(7727175)
 */
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
        go_back.setOnClickListener(v -> finish());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

    }


    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(CANBERRA, 13));
        mMap.getUiSettings().setZoomGesturesEnabled(false);
        mMap.getUiSettings().setZoomControlsEnabled(true);

        mMap.setOnMapClickListener(latLng -> {
            mMap.clear();
            mMap.addMarker(new MarkerOptions().position(latLng).title("Selected Location"));
            selectedLatitude = latLng.latitude;
            selectedLongitude = latLng.longitude;
            getAddress(selectedLatitude,selectedLongitude);
        });
    }

    private void getAddress(double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 10);
            if (addresses != null && addresses.size() > 0) {
                Set<String> addressSet = new HashSet<>();
                for (Address address : addresses) {
                    addressSet.add(address.getAddressLine(0));
                }

                List<String> addressList = new ArrayList<>(addressSet);

                ListView listView = findViewById(R.id.listView);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, addressList);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener((parent, view, position, id) -> {
                    Intent resultIntent = new Intent();
                    double[] result = {selectedLatitude, selectedLongitude};
                    resultIntent.putExtra("selectedPoint", result);
                    resultIntent.putExtra("selectedAddress",addressList.get(position));
                    setResult(RESULT_OK, resultIntent);
                    finish();
                });

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}