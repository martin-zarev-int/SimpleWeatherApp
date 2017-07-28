package com.martinzarev.weatherapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.martinzarev.weatherapp.Models.DailyForecast;
import com.martinzarev.weatherapp.Models.Weather.Weather;
import com.martinzarev.weatherapp.RecyclerViews.AllCitiesRecyclerView;

import java.util.ArrayList;

public class AllCitiesActivity extends AppCompatActivity {

    private RecyclerView citiesRVHolder;

    private AllCitiesRecyclerView citiesRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_cities);

        //Setting up fab
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.all_cities_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SearchCitiesActivity.class);
                startActivityForResult(intent,1);
            }
        });

        //init RV
        citiesRVHolder = (RecyclerView) findViewById(R.id.all_cities_recyclerView);
        citiesRVHolder.setLayoutManager(new LinearLayoutManager(this));

        //Setting up RV
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(citiesRVHolder.getContext(),
                LinearLayout.VERTICAL);
        citiesRVHolder.addItemDecoration(dividerItemDecoration);
        citiesRV = new AllCitiesRecyclerView(new ArrayList<DailyForecast>(),this);
        citiesRVHolder.setAdapter(citiesRV);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 1) {
            Bundle extra = data.getExtras();
            String ID = extra.getString("NameKey").trim();

        }
    }

}
