package com.martinzarev.weatherapp.Models.Weather;

import java.io.Serializable;

/**
 * Created by martin on 28.07.17.
 */

public class Main implements Serializable {

    private double temp;

    private double pressure;

    private double humidity;

    private double temp_min;

    private double temp_max;

    //Getters and setters for temp
    public double getTemp(){
        return temp;
    }
    public void setTemp(double temp){
        this.temp = temp;
    }

    //Getters and setters for pressure
    public double getPressure(){
        return pressure;
    }
    public void setPressure(double pressure){
        this.pressure = pressure;
    }

    //Getters and setters for humidity
    public double getHumidity(){
        return humidity;
    }
    public void setHumidity(double humidity){
        this.humidity = humidity;
    }

    //Getters and setters for temp_min
    public double getTempMin(){
        return temp_min;
    }
    public void setTempMin(double temp_min){
        this.temp_min = temp_min;
    }

    //Getters and setters for temp_max
    public double getTempMax(){
        return temp_max;
    }
    public void setTempMax(double temp_max){
        this.temp_max = temp_max;
    }
}
