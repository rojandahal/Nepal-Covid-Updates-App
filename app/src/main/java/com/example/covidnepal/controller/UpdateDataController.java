package com.example.covidnepal.controller;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.covidnepal.data.GetAllStatsData;

/**
 * This class will help get the data from the API and implement it
 */
public class UpdateDataController extends AppCompatActivity {

    GetAllStatsData getAllStatsData = GetAllStatsData.getInstance();

    public void updateAllDataFromAPI() {

            getAllStatsData.getWorldStatsDataFromAPI();
            getAllStatsData.getNepalStats();
            getAllStatsData.getProvinceCases();
            getAllStatsData.getDistrictCases();
            getAllStatsData.getGenderCasesData();
            getAllStatsData.getAgeGroupCasesData();
            getAllStatsData.getAllTestedData();
            getAllStatsData.getDistrictInfoMap();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
