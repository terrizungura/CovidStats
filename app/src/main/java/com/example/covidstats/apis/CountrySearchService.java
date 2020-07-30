package com.example.covidstats.apis;

import com.example.covidstats.models.CovidResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CountrySearchService {
    @GET("/statistics")
    Call<CovidResponse>searchStats(
            @Query("country") String country
    );
}
