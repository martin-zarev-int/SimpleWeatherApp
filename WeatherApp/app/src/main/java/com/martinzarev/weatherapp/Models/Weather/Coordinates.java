package com.martinzarev.weatherapp.Models.Weather;

import java.io.Serializable;

/**
 * Created by martin on 28.07.17.
 */

public class Coordinates implements Serializable {

    private double lon;

    private double lat;

    //getter and setter for lon
    public double getLon(){
        return lon;
    }
    public void setLon(double lon){
        this.lon = lon;
    }

    //getter and setter for lat
    public double getLat(){
        return lat;
    }
    public void setLat(double lat){
        this.lat = lat;
    }
}
