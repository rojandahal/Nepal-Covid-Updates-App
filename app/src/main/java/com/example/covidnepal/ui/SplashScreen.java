package com.example.covidnepal.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.covidnepal.MainNavigationActivity;
import com.example.covidnepal.controller.UpdateDataController;
import com.example.covidnepal.model.WorldStats;

public class SplashScreen extends AppCompatActivity {

    private UpdateDataController updateDataController = new UpdateDataController();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        updateDataController.updateAllDataFromAPI();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Start Activity when all data is fetched
                startActivity(new Intent(SplashScreen.this, MainNavigationActivity.class));
                finish();
            }
        }, 2000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
