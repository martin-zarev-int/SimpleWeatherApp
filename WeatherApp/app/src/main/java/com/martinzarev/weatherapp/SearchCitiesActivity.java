package com.martinzarev.weatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.martinzarev.weatherapp.Models.DailyForecast;
import com.martinzarev.weatherapp.RecyclerViews.SearchCitiesRecyclerView;
import com.martinzarev.weatherapp.VolleyRelated.RequestGeneator;
import com.martinzarev.weatherapp.VolleyRelated.SearchResponse;
import com.martinzarev.weatherapp.VolleyRelated.VolleySingleton;

import org.json.JSONObject;

import java.util.ArrayList;

public class SearchCitiesActivity extends AppCompatActivity {

    private RecyclerView searchedCitiesRVHolder;

    private SearchCitiesRecyclerView searchedCitiesRV;

    private EditText searchEditText;

    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_cities);

        //init RV
        searchedCitiesRVHolder = (RecyclerView) findViewById(R.id.search_cities_recyclerView);
        searchedCitiesRVHolder.setLayoutManager(new LinearLayoutManager(this));

        //init search editText and button
        searchEditText = (EditText) findViewById(R.id.search_cities_search_edit_text);
        searchButton = (Button) findViewById(R.id.search_cities_search_button);

        //Setting up RV
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(searchedCitiesRVHolder.getContext(),
                LinearLayout.VERTICAL);
        searchedCitiesRVHolder.addItemDecoration(dividerItemDecoration);
        searchedCitiesRV = new SearchCitiesRecyclerView(new ArrayList<DailyForecast>(),this);
        searchedCitiesRVHolder.setAdapter(searchedCitiesRV);

        //Setting onClickListeners
        searchButton.setOnClickListener(searchButtonOnClick);
    }

    View.OnClickListener searchButtonOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String searchedText = searchEditText.getText().toString();

            Response.Listener<JSONObject> responseListener = new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Gson gson = new Gson();
                    SearchResponse dailyForecasts =
                            gson.fromJson(response.toString(),SearchResponse.class);


                    searchedCitiesRV.addData(dailyForecasts.getList());
                }
            };

            JsonObjectRequest request = RequestGeneator.
                    generateSearchRequest(searchedText, responseListener);

            VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
        }
    };
}
