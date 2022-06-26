package com.example.location_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST = 12;
    private LocationManager locationManager;
    private LocationAdapter locationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.myLocations);
        locationAdapter = new LocationAdapter(this, R.layout.location_item, new ArrayList<>());
        listView.setAdapter(locationAdapter);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        getCurrentLocation();
    }
    private void getCurrentLocation(){
        if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this,"requestPerMission", Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST);
        }
        else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(@NonNull Location location) {
                    Toast.makeText(MainActivity.this, "Location updated", Toast.LENGTH_LONG).show();
                    Toast.makeText(MainActivity.this, "Latitude " + location.getLatitude(), Toast.LENGTH_LONG).show();
                    Toast.makeText(MainActivity.this, "Longitude " + location.getLatitude(), Toast.LENGTH_LONG).show();
                    locationAdapter.update(new MyLocation(location.getLatitude() + "", location.getLongitude() + ""));
                }

                @Override
                public void onProviderEnabled(@NonNull String provider) {
                    LocationListener.super.onProviderEnabled(provider);
                }

                @Override
                public void onProviderDisabled(@NonNull String provider) {
                    LocationListener.super.onProviderDisabled(provider);
                }
            });
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PERMISSION_REQUEST && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            getCurrentLocation();
        }
    }
}