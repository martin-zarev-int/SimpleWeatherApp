package com.martinzarev.weatherapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import org.w3c.dom.Text;

import java.util.ArrayList;

public class AllCitiesActivity extends AppCompatActivity {

    private RecyclerView citiesRVHolder;

    private AllCitiesRecyclerView citiesRV;

    private TextView noCitiesAddedTextView;

    private FloatingActionButton fab;

    private SwipeRefreshLayout swipeRefreshLayout;

    private SharedPreferences preferences;

    private SharedPreferences.Editor editor;

    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_cities);

        //init content
        noCitiesAddedTextView = (TextView) findViewById(R.id.all_cities_no_saved_cities);
        citiesRVHolder = (RecyclerView) findViewById(R.id.all_cities_recyclerView);
        fab = (FloatingActionButton) findViewById(R.id.all_cities_fab);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.all_cities_swipe_to_refresh);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.all_cities_coordinator_layout);

        //Setting up editor
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        //init RV
        citiesRVHolder.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(citiesRVHolder.getContext(),
                LinearLayout.VERTICAL);
        citiesRVHolder.addItemDecoration(dividerItemDecoration);
        citiesRV = new AllCitiesRecyclerView(new ArrayList<DailyForecast>(),this);
        citiesRVHolder.setAdapter(citiesRV);

        //inflate saved data
        String savedCities = preferences.getString("SavedCities",null);
        DailyForecast[] savedDailyForecasts=null;
        if(savedCities != null){
            Gson gson = new Gson();
            savedDailyForecasts = gson.fromJson(savedCities,DailyForecast[].class);
            if(savedDailyForecasts != null && savedDailyForecasts.length>0){
                noCitiesAddedTextView.setVisibility(View.GONE);
            }
            for(DailyForecast tempSavedDailyForecast : savedDailyForecasts){
                citiesRV.addData(tempSavedDailyForecast);
            }
        }

        //Setting up swipe to delete
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                final int itemPosition = viewHolder.getAdapterPosition();
                final DailyForecast tempDeletedDailyForecast = citiesRV.eraseAtPosition(itemPosition);
                updateSavedCities();
                if(citiesRV.getItemCount() == 0){
                    noCitiesAddedTextView.setVisibility(View.VISIBLE);
                }
                Snackbar snackbar = Snackbar.make(coordinatorLayout, R.string.deleted_city, Snackbar.LENGTH_LONG).setAction(R.string.undo, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        citiesRV.addDataAtPosition(itemPosition, tempDeletedDailyForecast);
                        Snackbar restoreSnackbar = Snackbar.make(coordinatorLayout,R.string.restored_city, Snackbar.LENGTH_LONG);
                        restoreSnackbar.show();
                        noCitiesAddedTextView.setVisibility(View.GONE);
                        updateSavedCities();
                    }
                });
                snackbar.show();

            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(citiesRVHolder);


        //Setting up fab
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(citiesRV.getItemCount() == 20){
                    Snackbar snackbar = Snackbar.make(coordinatorLayout,R.string.maximum_allowed_cities,Snackbar.LENGTH_LONG);
                    snackbar.show();
                }else{
                    Intent intent = new Intent(getApplicationContext(),SearchCitiesActivity.class);
                    startActivityForResult(intent,1);
                }
            }
        });

        //setting up swipe to refresh
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ArrayList<Integer> allCitiesIds = citiesRV.getAllIds();
                if(allCitiesIds.size()==0){
                    swipeRefreshLayout.setRefreshing(false);
                }else{
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
            noCitiesAddedTextView.setVisibility(View.GONE);
            citiesRV.addData(addedDailyForecast);

            updateSavedCities();
        }
    }

    private void updateSavedCities(){
        ArrayList<DailyForecast> newestDailyForeCasts = citiesRV.getAllData();

        Gson gson = new Gson();
        String json = gson.toJson(newestDailyForeCasts);

        editor.putString("SavedCities",json);
        editor.apply();
    }

}
