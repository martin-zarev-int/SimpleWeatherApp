package com.martinzarev.weatherapp.RecyclerViews;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.martinzarev.weatherapp.Models.DailyForecast;
import com.martinzarev.weatherapp.R;

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
        private ImageButton addRemoveButton;

        public SearchViewHolder(View itemView) {
            super(itemView);

            //Init the views
            cityNameTV = itemView.findViewById(R.id.search_city_rv_item_name);
            countryName = itemView.findViewById(R.id.search_city_rv_item_country);
            cityDegreesTV = itemView.findViewById(R.id.search_city_rv_item_degree);
            addRemoveButton = itemView.findViewById(R.id.search_city_rv_item_add_remove);
        }

        void bindTo(final DailyForecast currentCityWeather){
            cityNameTV.setText(currentCityWeather.getName());
            countryName.setText(currentCityWeather.getSystemSpecific().getCountry());
            cityDegreesTV.setText(String.valueOf(currentCityWeather.getMain().getTemp()));
        }
    }

    public void addData(ArrayList<DailyForecast> forecasts){
        dailyForecasts.addAll(forecasts);
    }
}
