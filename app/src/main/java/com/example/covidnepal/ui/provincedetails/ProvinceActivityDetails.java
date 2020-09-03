package com.example.covidnepal.ui.provincedetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.covidnepal.R;

public class ProvinceActivityDetails extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProvinceDetailsAdapter provinceDetailsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_province_details);
        setToolbar();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        provinceDetailsAdapter = new ProvinceDetailsAdapter(this);
        recyclerView.setAdapter(provinceDetailsAdapter);
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