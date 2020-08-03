package com.example.covidstats.apis;

import com.example.covidstats.models.Statistics;
import com.example.covidstats.models.StatsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface CovidStatsSearchService {
    @GET("/statistics")
    Call<StatsResponse> searchStatsByCountry(
            @Query("country") String country
    );

    @GET("/statistics")
    Call<StatsResponse> searchStats();
}
