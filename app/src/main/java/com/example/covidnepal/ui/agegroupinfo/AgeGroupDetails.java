package com.example.covidnepal.ui.agegroupinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.covidnepal.R;

public class AgeGroupDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_group_details);
        setToolbar();

        RecyclerView recyclerView = findViewById(R.id.ageGroup_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        AgeGroupAdapter adapter = new AgeGroupAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.ageGroupToolbar);
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