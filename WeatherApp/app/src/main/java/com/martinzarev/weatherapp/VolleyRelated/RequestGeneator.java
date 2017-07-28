package com.martinzarev.weatherapp.VolleyRelated;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

/**
 * Created by martin on 28.07.17.
 */

public class RequestGeneator {

    private static final String APP_KEY = "&appid=fd6df0ba1500b879b2966fe6f8734836";

    private static final String API_URL = "http://api.openweathermap.org/data/2.5/";

    private static final String SEARCH_FIRST_PART = "find?q=";

    private static final String SEARCH_SECOND_PART = "&type=like";


    public static JsonObjectRequest generateSearchRequest(String searchedText,
                                                          Response.Listener<JSONObject> responseListener){

        String searchUrl = API_URL + SEARCH_FIRST_PART + searchedText + SEARCH_SECOND_PART + APP_KEY;

        JsonObjectRequest jsObjRequestSearch = new JsonObjectRequest
                (Request.Method.GET, searchUrl, null, responseListener, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //report error
                    }
                });


        return jsObjRequestSearch;
    }
}
