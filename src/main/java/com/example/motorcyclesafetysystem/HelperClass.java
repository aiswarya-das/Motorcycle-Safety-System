package com.example.motorcyclesafetysystem;

import android.net.Uri;

public class HelperClass {

    String location;
    String date;
    public HelperClass(String location, String date) {
        this.location = location;
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
