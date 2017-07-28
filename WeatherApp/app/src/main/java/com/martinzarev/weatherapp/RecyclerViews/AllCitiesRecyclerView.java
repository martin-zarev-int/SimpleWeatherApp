package com.martinzarev.weatherapp.RecyclerViews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.martinzarev.weatherapp.Models.Weather;
import com.martinzarev.weatherapp.R;

import java.util.ArrayList;

/**
 * Created by martin on 28.07.17.
 */

public class AllCitiesRecyclerView extends RecyclerView.Adapter<AllCitiesRecyclerView.ViewHolder> {

    //The data for the RV
    private ArrayList<Weather> cities;

    //Context for inflater
    private Context context;

    public AllCitiesRecyclerView(ArrayList<Weather> cities, Context context){
        this.cities = cities;
        this.context = context;
    }

    @Override
    public AllCitiesRecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.city_rv_item,parent,false));
    }

    @Override
    public void onBindViewHolder(AllCitiesRecyclerView.ViewHolder holder, int position) {
        Weather currentCityWeather = cities.get(position);

        holder.bindTo(currentCityWeather);
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView cityNameTV, cityDegreesTV;

        public ViewHolder(View itemView) {
            super(itemView);

            //Init the views
            cityNameTV = itemView.findViewById(R.id.city_rv_item_name);
            cityDegreesTV = itemView.findViewById(R.id.city_rv_item_degree);

        }

        void bindTo(final Weather currentCityWeather){
            cityNameTV.setText(currentCityWeather.getCityName());
            cityDegreesTV.setText(String.valueOf(currentCityWeather.getTemperature()));
        }
    }
}
