package com.martinzarev.weatherapp.Models.Weather;

/**
 * Created by martin on 28.07.17.
 */

public class Wind {

    private double speed;

    private double deg;

    //Getter and setter for wind speed
    public double getSpeed(){
        return speed;
    }
    public void setSpeed(double speed){
        this.speed = speed;
    }

    //Getter and setter for wind temp
    public double getDeg(){
        return deg;
    }
    public void setDeg(double deg){
        this.deg=deg;
    }
}
