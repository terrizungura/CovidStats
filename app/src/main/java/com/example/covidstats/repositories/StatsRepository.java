package com.example.covidstats.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.covidstats.apis.CovidStatsSearchService;
import com.example.covidstats.models.Statistics;
import com.example.covidstats.models.StatsResponse;

public class StatsRepository {

    public final String COVID_STATS_SERVICE_BASE_URL = "https://covid-193.p.rapidapi.com/";

    private CovidStatsSearchService covidStatsSearchService;
    private MutableLiveData<StatsResponse> statsResponseLiveData;

    public StatsRepository(){
        statsResponseLiveData = new MutableLiveData<>();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        covidStatsSearchService = new retrofit2.Retrofit.Builder()
                .baseUrl(COVID_STATS_SERVICE_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CovidStatsSearchService.class);
    }

    public void searchStats(String country, String apiKey){
        covidStatsSearchService.searchStats(country, apiKey)
                .enqueue(new Callback<StatsResponse>() {
                    @Override
                    public void onResponse(Call<StatsResponse> call, Response<StatsResponse> response) {
                        if(response.body()!= null){
                            statsResponseLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<StatsResponse> call, Throwable t) {

                        statsResponseLiveData.postValue(null);
                    }
                });
    }

    public LiveData<StatsResponse> getStatsResponseLiveData(){
        return statsResponseLiveData;
    }
}
