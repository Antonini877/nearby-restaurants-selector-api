package com.api.app.service;
import java.lang.Math;


class Coordinate{
    private double latitude;
    private double longitude;

    public Coordinate(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude(){
        return this.latitude;
    }

    public double getLongitude(){
        return this.longitude;
    }

    public double CalculateDistance( Coordinate other ){
        return Math.sqrt((other.latitude-this.latitude)*(other.longitude-this.longitude));
    }

}




