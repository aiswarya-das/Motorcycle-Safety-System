package com.example.motorcyclesafetysystem;

public class recycleData {
    String location;
    String date;

    public recycleData(String location, String date) {
        this.location = location;
        this.date = date;
    }
    public recycleData(){}
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
