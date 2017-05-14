package com.pulkit.demon.chemistandbloodbanklocator.list;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Lincoln on 15/01/16.
 */
public class Movie {
    private String title, genre;
    private double distance;
    LatLng location;
    int id;

    public Movie() {
    }

    public Movie(String title, String genre, double dis,LatLng loc,int id) {
        this.title = title;
        this.genre = genre;
        this.distance= dis;
        this.location=loc;
        this.id=id;


    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
