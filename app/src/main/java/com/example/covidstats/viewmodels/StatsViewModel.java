package com.example.covidstats.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.covidstats.models.Statistics;
import com.example.covidstats.models.StatsResponse;
import com.example.covidstats.repositories.StatsRepository;

import io.github.cdimascio.dotenv.Dotenv;

public class StatsViewModel extends AndroidViewModel {
    private StatsRepository statsRepository;
    private LiveData<StatsResponse> statsResponseLiveData;

    public StatsViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(){
        statsRepository = new StatsRepository();
        statsResponseLiveData = statsRepository.getStatsResponseLiveData();
    }

    public void searchStats(String country){
        Dotenv dotenv = Dotenv.configure().directory("/asset").filename("env").load();
        statsRepository.searchStats(country, dotenv.get("5b4049b3dcmsh22ff97160b6ca06p15c6acjsn2c0ffeede1ea"));
    }

    public LiveData<StatsResponse> getStatsResponseLiveData() {
        return statsResponseLiveData;
    }
}
