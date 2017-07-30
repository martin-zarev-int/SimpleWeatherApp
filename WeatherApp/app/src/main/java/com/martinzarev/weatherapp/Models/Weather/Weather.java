package com.martinzarev.weatherapp.Models.Weather;

import java.io.Serializable;

/**
 * Created by martin on 28.07.17.
 */

public class Weather implements Serializable {

    private String main;

    private String description;

    private String icon;

    //getter and setter for name
    public String getMain(){
        return main;
    }
    public void setMain(String main){
        this.main = main;
    }

    //getter and setter for description
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    //getter and setter for humidity
    public String getIcon(){
        return icon;
    }
    public void setIcon(String icon){
        this.icon = icon;
    }
}
