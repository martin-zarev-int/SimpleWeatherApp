package com.martinzarev.weatherapp.Models.Weather;

import java.io.Serializable;

/**
 * Created by martin on 28.07.17.
 */

public class Clouds implements Serializable{

    private double all;

    //Getter and setter
    public double getAll(){
        return all;
    }
    public void setAll(double all){
        this.all=all;
    }
}
