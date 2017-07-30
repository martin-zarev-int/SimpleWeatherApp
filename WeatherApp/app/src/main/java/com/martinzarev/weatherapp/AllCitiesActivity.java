package com.martinzarev.weatherapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.martinzarev.weatherapp.Models.DailyForecast;
import com.martinzarev.weatherapp.Models.Weather.Weather;
import com.martinzarev.weatherapp.RecyclerViews.AllCitiesRecyclerView;
import com.martinzarev.weatherapp.VolleyRelated.RequestGeneator;
import com.martinzarev.weatherapp.VolleyRelated.SearchResponse;
import com.martinzarev.weatherapp.VolleyRelated.VolleySingleton;

import org.json.JSONObject;

import java.util.ArrayList;

public class AllCitiesActivity extends AppCompatActivity {

    private RecyclerView citiesRVHolder;

    private AllCitiesRecyclerView citiesRV;

    private SwipeRefreshLayout swipeRefreshLayout;

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

        //init swipe to refresh
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.all_cities_swipe_to_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ArrayList<Integer> allCitiesIds = citiesRV.getAllIds();

                Response.Listener<JSONObject> responseListener = new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        SearchResponse dailyForecasts =
                                gson.fromJson(response.toString(),SearchResponse.class);
                        swipeRefreshLayout.setRefreshing(false);
                        citiesRV.eraseData();
                        citiesRV.addData(dailyForecasts.getList());
                    }
                };

                JsonObjectRequest allIdsRequest = RequestGeneator.generateLoadRequest(allCitiesIds, responseListener);
                VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(allIdsRequest);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == -1) {
            Bundle extra = data.getExtras();
            DailyForecast addedDailyForecast = (DailyForecast)extra.getSerializable("new_city");
            citiesRV.addData(addedDailyForecast);
        }
    }

}
