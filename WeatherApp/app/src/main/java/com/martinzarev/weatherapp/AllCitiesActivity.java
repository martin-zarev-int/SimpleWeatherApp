package com.martinzarev.weatherapp;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.martinzarev.weatherapp.Models.Weather;
import com.martinzarev.weatherapp.RecyclerViews.AllCitiesRecyclerView;

import java.util.ArrayList;

public class AllCitiesActivity extends AppCompatActivity {

    private RecyclerView citiesRVHolder;

    private AllCitiesRecyclerView citiesRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_cities);

        //init RV
        citiesRVHolder = (RecyclerView) findViewById(R.id.all_cities_recyclerView);
        citiesRVHolder.setLayoutManager(new LinearLayoutManager(this));


        //Setting up fab
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.all_cities_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go to second activity
            }
        });

        //Settin up RV
        citiesRV = new AllCitiesRecyclerView(createCities(),this);
        citiesRVHolder.setAdapter(citiesRV);

    }

    private ArrayList<Weather> createCities(){
        ArrayList<Weather> cities = new ArrayList<>();

        for(int i = 22; i<100; i++){
            Weather tempCity = new Weather();
            tempCity.setCityName("City " + i);
            tempCity.setTemperature(i);
            cities.add(tempCity);
        }
        return cities;
    }
}
