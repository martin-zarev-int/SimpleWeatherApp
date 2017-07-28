package com.martinzarev.weatherapp.Models;

import com.martinzarev.weatherapp.Models.Weather.Clouds;
import com.martinzarev.weatherapp.Models.Weather.Coordinates;
import com.martinzarev.weatherapp.Models.Weather.Main;
import com.martinzarev.weatherapp.Models.Weather.SystemSpecific;
import com.martinzarev.weatherapp.Models.Weather.Weather;
import com.martinzarev.weatherapp.Models.Weather.Wind;

/**
 * Created by martin on 28.07.17.
 */

public class DailyForecast {

    private Coordinates coord;

    private SystemSpecific sys;

    private Weather weather;

    private Main main;

    private int visibility;

    private Wind wind;

    private Clouds clouds;

    private int id;

    private String name;

    //Getters and setters for coordinates
    public Coordinates getCoordinates(){
        return coord;
    }
    public void setCoordinates(Coordinates coord){
        this.coord = coord;
    }

    //Getters and setters for system specific
    public SystemSpecific getSystemSpecific(){
        return sys;
    }
    public void setSystemSpecific(SystemSpecific sys){
        this.sys = sys;
    }

    //Getters and setters for weather
    public Weather getWeather(){
        return weather;
    }
    public void setWeather(Weather weather){
        this.weather = weather;
    }

    //Getters and setters for main
    public Main getMain(){
        return main;
    }
    public void setMain(Main main){
        this.main = main;
    }

    //Getters and setters for visibility
    public int getVisibility(){
        return visibility;
    }
    public void setVisibility(int visibility){
        this.visibility = visibility;
    }

    //Getters and setters for wind
    public Wind getWind(){
        return wind;
    }
    public void setWind(Wind wind){
        this.wind = wind;
    }

    //Getters and setters for clouds
    public Clouds getClouds(){
        return clouds;
    }
    public void setClouds(Clouds clouds){
        this.clouds = clouds;
    }

    //Getters and setters for id
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    //Getters and setters for name
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
}
