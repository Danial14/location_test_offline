package com.example.location_test;

public class MyLocation {
    private String latitude;
    private String longitude;
    MyLocation(String latitude, String longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
