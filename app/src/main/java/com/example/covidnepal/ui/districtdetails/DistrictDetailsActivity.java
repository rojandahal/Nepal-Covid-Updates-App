package com.example.covidnepal.ui.districtdetails;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import android.widget.EditText;


import com.example.covidnepal.R;
import com.example.covidnepal.data.GetAllStatsData;
import com.example.covidnepal.model.DistrictInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DistrictDetailsActivity extends AppCompatActivity {

    //private static final String TAG = "District Activity";

    private DistrictDetailsAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district_details);
        setToolbar();

        recyclerView = findViewById(R.id.district_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DistrictDetailsAdapter(this);
        recyclerView.setAdapter(adapter);
        //Log.d(TAG, "onItemCount: " + adapter.getItemCount());

    }

    private void setToolbar(){
        Toolbar toolbar = findViewById(R.id.districtToolbar);
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