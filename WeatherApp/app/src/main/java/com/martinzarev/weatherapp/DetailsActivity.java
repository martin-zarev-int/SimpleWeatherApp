package com.martinzarev.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.martinzarev.weatherapp.Models.DailyForecast;

public class DetailsActivity extends AppCompatActivity {

    private TextView cityNameTV,tempMainTV,humidityMainTV,descriptionTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //init content
        cityNameTV = (TextView) findViewById(R.id.details_activity_city_name);
        tempMainTV = (TextView) findViewById(R.id.details_activity_temperature_main);
        humidityMainTV = (TextView) findViewById(R.id.details_activity_humidity_main);
        descriptionTV = (TextView) findViewById(R.id.details_activity_description);

        Bundle b = getIntent().getExtras();
        DailyForecast detailedForeCast = (DailyForecast)b.getSerializable("detail_city");

        cityNameTV.setText(detailedForeCast.getName());
        tempMainTV.setText(String.valueOf(detailedForeCast.getMain().getTemp()));
        humidityMainTV.setText(String.valueOf(detailedForeCast.getMain().getHumidity()));
        if(detailedForeCast.getWeather() != null && detailedForeCast.getWeather().size()>0){
            descriptionTV.setText(detailedForeCast.getWeather().get(0).getDescription());
        }

    }
}
