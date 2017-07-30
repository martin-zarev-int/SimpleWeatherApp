package com.martinzarev.weatherapp.RecyclerViews;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.pm.ActivityInfoCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.martinzarev.weatherapp.Models.DailyForecast;
import com.martinzarev.weatherapp.R;
import com.martinzarev.weatherapp.SearchCitiesActivity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by martin on 28.07.17.
 */

public class SearchCitiesRecyclerView extends RecyclerView.Adapter<SearchCitiesRecyclerView.SearchViewHolder> {

    //The data for the RV
    private ArrayList<DailyForecast> dailyForecasts;

    //Context for inflater
    private Context context;

    public SearchCitiesRecyclerView(ArrayList<DailyForecast> dailyForecasts, Context context){
        this.dailyForecasts = dailyForecasts;
        this.context = context;
    }

    @Override
    public SearchCitiesRecyclerView.SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchViewHolder(LayoutInflater.from(context).inflate(R.layout.search_city_rv_item,parent,false));
    }

    @Override
    public void onBindViewHolder(SearchCitiesRecyclerView.SearchViewHolder holder, int position) {
        final DailyForecast currentDailyForecast = dailyForecasts.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,currentDailyForecast.getName(),Toast.LENGTH_LONG);
            }
        });

        holder.bindTo(currentDailyForecast);
    }

    @Override
    public int getItemCount() {
        return dailyForecasts.size();
    }

    class SearchViewHolder extends RecyclerView.ViewHolder{

        private TextView cityNameTV, countryName, cityDegreesTV;
        private ImageButton addButton;

        public SearchViewHolder(View itemView) {
            super(itemView);

            //Init the views
            cityNameTV = itemView.findViewById(R.id.search_city_rv_item_name);
            countryName = itemView.findViewById(R.id.search_city_rv_item_country);
            cityDegreesTV = itemView.findViewById(R.id.search_city_rv_item_degree);
            addButton = itemView.findViewById(R.id.search_city_rv_item_add);
        }

        void bindTo(final DailyForecast currentDailyForecast){
            cityNameTV.setText(currentDailyForecast.getName());
            countryName.setText(currentDailyForecast.getSystemSpecific().getCountry());
            cityDegreesTV.setText(String.valueOf(currentDailyForecast.getMain().getTemp()));

            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent returnInten = new Intent();
                    returnInten.putExtra("new_city",currentDailyForecast);
                    ((SearchCitiesActivity)context).setResult(Activity.RESULT_OK, returnInten);
                    ((SearchCitiesActivity)context).finish();
                }
            });
        }
    }

    public void addData(ArrayList<DailyForecast> forecasts){
        dailyForecasts.addAll(forecasts);
        notifyDataSetChanged();
    }
}
