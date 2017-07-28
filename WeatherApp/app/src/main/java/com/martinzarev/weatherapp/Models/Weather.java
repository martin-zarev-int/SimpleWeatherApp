package com.martinzarev.weatherapp.Models;

/**
 * Created by martin on 28.07.17.
 */

public class Weather {

    private String cityName;

    private double temperature;

    private double humidity;

    private String description;

    //getter and setter for name
    public String getCityName(){
        return cityName;
    }
    public void setCityName(String cityName){
        this.cityName = cityName;
    }

    //getter and setter for temp
    public double getTemperature(){
        return temperature;
    }
    public void setTemperature(double temperature){
        this.temperature = temperature;
    }

    //getter and setter for humidity
    public double getHumidity(){
        return humidity;
    }
    public void setHumidity(double humidity){
        this.humidity = humidity;
    }

    //getter and setter for description
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }

}
