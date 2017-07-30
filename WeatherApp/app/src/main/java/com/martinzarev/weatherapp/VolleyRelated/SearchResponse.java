package com.martinzarev.weatherapp.VolleyRelated;

import com.martinzarev.weatherapp.Models.DailyForecast;

import java.util.ArrayList;

/**
 * Created by martin on 28.07.17.
 */

public class SearchResponse {

    private String message;

    private String cod;

    private int count;

    private ArrayList<DailyForecast> list;

    public String getMessage(){
        return message;
    }
    public void setMessage(String message){
        this.message = message;
    }

    public String getCod(){
        return cod;
    }
    public void setCod(String cod){
        this.cod = cod;
    }

    public int getCount(){
        return count;
    }
    public void setCount(int count){
        this.count = count;
    }

    public ArrayList<DailyForecast> getList() {
        return list;
    }

    public void setList(ArrayList<DailyForecast> list) {
        this.list = list;
    }
}
