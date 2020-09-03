package com.example.covidnepal.ui.genderinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

import com.example.covidnepal.R;
import com.example.covidnepal.data.GetAllStatsData;
import com.example.covidnepal.model.GenderInfo;
import com.example.covidnepal.model.NepalStats;

import java.text.NumberFormat;
import java.util.Map;

public class GenderInfoDetails extends AppCompatActivity {

    private Map<String,GenderInfo> gInfo = GetAllStatsData.getInstance().genderInstance();
    private NumberFormat nf = NumberFormat.getInstance();
    private TextView mCases;
    private TextView mActive;
    private TextView mDeath;
    private TextView mRecovered;
    private TextView _casesFem;
    private TextView _activeFem;
    private TextView _deathFem;
    private TextView _recoveredFem;
    private TextView _casesUnk;
    private TextView _activeUnk;
    private TextView _deathUnk;
    private TextView _recoveredUnk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender_info);
        setToolbar();
        initializeViews();

        GenderInfo info = gInfo.get("male");
        setMaleTextView(info);
        info = gInfo.get("female");
        setFemaleTextView(info);
        info = gInfo.get("null");
        setUnknownTextView(info);
    }

    private void setUnknownTextView(GenderInfo info) {
        _casesUnk.setText(nf.format(info.getCases()));
        _activeUnk.setText(nf.format(info.getActive()));
        _recoveredUnk.setText(nf.format(info.getRecovered()));
        _deathUnk.setText(nf.format(info.getDeaths()));
    }

    private void setFemaleTextView(GenderInfo info) {
        _casesFem.setText(nf.format(info.getCases()));
        _activeFem.setText(nf.format(info.getActive()));
        _recoveredFem.setText(nf.format(info.getRecovered()));
        _deathFem.setText(nf.format(info.getDeaths()));
    }

    private void setMaleTextView(GenderInfo info) {
        mCases.setText(nf.format(info.getCases()));
        mActive.setText(nf.format(info.getActive()));
        mRecovered.setText(nf.format(info.getRecovered()));
        mDeath.setText(nf.format(info.getDeaths()));
    }

    private void initializeViews() {
        mCases = findViewById(R.id.genderMale_cases_number);
        mActive = findViewById(R.id.genderMale_active_number);
        mRecovered = findViewById(R.id.genderMale_recovered_number);
        mDeath = findViewById(R.id.genderMale_deaths_number);
        _casesFem = findViewById(R.id.genderFeMale_cases_number);
        _activeFem = findViewById(R.id.genderFeMale_active_number);
        _recoveredFem = findViewById(R.id.genderFeMale_recovered_number);
        _deathFem = findViewById(R.id.genderFeMale_deaths_number);
        _activeUnk = findViewById(R.id.genderUnknown_active_number);
        _casesUnk = findViewById(R.id.genderUnknown_cases_number);
        _recoveredUnk = findViewById(R.id.genderUnknown_recovered_number);
        _deathUnk = findViewById(R.id.genderUnknown_deaths_number);
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.genderToolbar);
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
}