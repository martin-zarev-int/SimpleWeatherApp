package com.martinzarev.weatherapp.Models.Weather;

import java.io.Serializable;

/**
 * Created by martin on 28.07.17.
 */

public class SystemSpecific implements Serializable {

    private String country;

    private long sunrise;

    private long sunset;

    //Getters and setters for country
    public String getCountry(){
        return country;
    }
    public void setCountry(String country){
        this.country = country;
    }

    //Getters and setters for sunrise
    public long getSunrise(){
        return sunrise;
    }
    public void setSunrise(long sunrise){
        this.sunrise = sunrise;
    }

    //Getters and setters for sunset
    public long getSunset(){
        return sunset;
    }
    public void setSunset(long sunset){
        this.sunset = sunset;
    }
}
