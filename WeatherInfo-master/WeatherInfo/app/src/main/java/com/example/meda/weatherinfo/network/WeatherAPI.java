package com.example.meda.weatherinfo.network;

import com.example.meda.weatherinfo.data.WeatherResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by meda on 5/2/16.
 */
public interface WeatherAPI {

    @GET("weather")
    Call<WeatherResult> getCurrentWeatherData(@Query("q") String q,
                                              @Query("units") String units,
                                              @Query("appid") String appid
    );

}
