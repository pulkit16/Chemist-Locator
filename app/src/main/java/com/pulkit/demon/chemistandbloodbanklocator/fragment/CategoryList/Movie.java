package com.pulkit.demon.chemistandbloodbanklocator.fragment.CategoryList;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Lincoln on 15/01/16.
 */
public class Movie {
    private String title, genre;
    private double distance;
    LatLng location;

    public Movie() {
    }

    public Movie(String title, String genre, double dis,LatLng loc) {
        this.title = title;
        this.genre = genre;
        this.distance= dis;
        this.location=loc;


    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getYear() {
        return Double.toString(distance);
    }

    public void setYear(double dis) {
        this.distance = dis;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng loc) {
        this.location = loc;
    }

}
