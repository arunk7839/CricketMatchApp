package com.example.lenovo.cricketmatchapp.rest;


import com.example.lenovo.cricketmatchapp.model.CalenderResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiInterface {


    @GET("api/matchCalendar")
    Call<CalenderResponse> getMatchCalender(@Query("apikey") String apiKey);


}
