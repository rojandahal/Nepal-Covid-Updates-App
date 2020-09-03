package com.example.covidnepal.ui.worlddetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

import com.example.covidnepal.R;
import com.example.covidnepal.model.WorldStats;

import java.text.NumberFormat;

public class WorldDetailsActivity extends AppCompatActivity {

    private WorldStats ws = WorldStats.getInstance();
    private NumberFormat nf = NumberFormat.getInstance();
    private TextView testsDone;
    private TextView affectedCountries;
    private TextView wCases;
    private TextView mActive;
    private TextView mRecovered;
    private TextView mDeaths;
    private TextView mTodayCases;
    private TextView mTodayRecovered;
    private TextView mTodayDeaths;
    private TextView mCriticalCases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world_details);

        setToolbar();
        initializeViews();
        setTextViews();

    }

    private void setTextViews(){
        testsDone.setText(nf.format(ws.getTestsDone()));
        affectedCountries.setText(nf.format(ws.getAffectedCountries()));
        wCases.setText(nf.format(ws.getCases()));
        mActive.setText(nf.format(ws.getActive()));
        mRecovered.setText(nf.format(ws.getRecovered()));
        mDeaths.setText(nf.format(ws.getDeaths()));
        mTodayCases.setText(nf.format(ws.getTodayCases()));
        mTodayRecovered.setText(nf.format(ws.getTodayRecovered()));
        mTodayDeaths.setText(nf.format(ws.getTodayDeaths()));
        mCriticalCases.setText(nf.format(ws.getCritical()));
    }

    private void initializeViews(){
        testsDone = findViewById(R.id.world_testsDone);
        affectedCountries = findViewById(R.id.world_affectedCountries);
        wCases = findViewById(R.id.world_cases_number);
        mActive = findViewById(R.id.world_active_number);
        mRecovered = findViewById(R.id.world_recovered_number);
        mDeaths = findViewById(R.id.world_deaths_number);
        mTodayCases = findViewById(R.id.world_todayCases_number);
        mTodayRecovered = findViewById(R.id.world_todayRecovered_number);
        mTodayDeaths = findViewById(R.id.world_todayDeaths_number);
        mCriticalCases = findViewById(R.id.world_critical_number);
    }

    private void setToolbar(){
        Toolbar toolbar = findViewById(R.id.provinceToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}