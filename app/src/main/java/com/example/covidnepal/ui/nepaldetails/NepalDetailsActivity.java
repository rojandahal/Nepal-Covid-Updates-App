package com.example.covidnepal.ui.nepaldetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

import com.example.covidnepal.R;
import com.example.covidnepal.model.NepalStats;

import java.text.NumberFormat;

public class NepalDetailsActivity extends AppCompatActivity {

    private NepalStats ns = NepalStats.getINSTANCE();
    private NumberFormat nf = NumberFormat.getInstance();
    private TextView cases;
    private TextView active;
    private TextView death;
    private TextView recovered;

    private TextView tested_total;
    private TextView tested_negative;
    private TextView tested_rdt;
    private TextView quarantined;
    private TextView in_isolation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nepal_details);

        setToolBar();
        initializeViews();
        setTextViews();
    }

    private void setTextViews(){
        cases.setText(nf.format(ns.getCases()));
        active.setText(nf.format(ns.getActive()));
        death.setText(nf.format(ns.getDeath()));
        recovered.setText(nf.format(ns.getRecovered()));
        tested_total.setText(nf.format(ns.getTested_total()));
        tested_negative.setText(nf.format(ns.getTested_negative()));
        tested_rdt.setText(nf.format(ns.getTested_rdt()));
        quarantined.setText(nf.format(ns.getQuarantined()));
        in_isolation.setText(nf.format(ns.getIn_isolation()));
    }

    private void initializeViews(){
        cases = findViewById(R.id.nepalDetail_cases_number);
        active = findViewById(R.id.nepalDetail_active_number);
        death = findViewById(R.id.nepalDetail_deaths_number);
        recovered = findViewById(R.id.nepalDetail_recovered_number);
        tested_total = findViewById(R.id.nepal_testsDone);
        tested_negative = findViewById(R.id.nepal_testedNegative_number);
        tested_rdt = findViewById(R.id.nepalDetails_rdtTest_number);
        quarantined = findViewById(R.id.nepal_todayQuarantined_number);
        in_isolation = findViewById(R.id.nepal_inIsolation_number);
    }

    private void setToolBar(){
        Toolbar toolbar = findViewById(R.id.nepalToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();

    }
}