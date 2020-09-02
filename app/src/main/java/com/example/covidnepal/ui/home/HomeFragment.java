package com.example.covidnepal.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.covidnepal.R;
import com.example.covidnepal.model.NepalStats;
import com.example.covidnepal.model.WorldStats;

import org.w3c.dom.Text;

import java.text.NumberFormat;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private String TAG = "Home Fragment";

    private WorldStats worldStats = WorldStats.getInstance();
    private NepalStats nepalStats = NepalStats.getINSTANCE();
    private NumberFormat numberFormat = NumberFormat.getInstance();
    private TextView globalCases, nepCases;
    private TextView globalRecovered, nepRecovered;
    private TextView globalDeaths, nepDeaths;

    private TextView provinceDetails;
    private TextView districtDetails;
    private TextView municipalityDetails;
    private TextView genderDetails;
    private TextView ageGroupDetails;
    private CardView worldStatCard;
    private CardView nepalStatCard;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        globalCases = root.findViewById(R.id.global_cases_number);
        globalRecovered = root.findViewById(R.id.global_recovered_number);
        globalDeaths = root.findViewById(R.id.global_deaths_number);
        nepCases = root.findViewById(R.id.nepal_cases_number);
        nepRecovered = root.findViewById(R.id.nepal_recovered_number);
        nepDeaths = root.findViewById(R.id.nepal_deaths_number);
        provinceDetails = root.findViewById(R.id.provinceInfo_text);
        districtDetails = root.findViewById(R.id.districtInfo_text);
        municipalityDetails = root.findViewById(R.id.municipalityInfo_text);
        genderDetails = root.findViewById(R.id.genderInfo_text);
        ageGroupDetails = root.findViewById(R.id.ageGroup_text);
        worldStatCard = root.findViewById(R.id.worldStatsCard);
        nepalStatCard = root.findViewById(R.id.nepalStatsCard);

        globalCases.setText(numberFormat.format(worldStats.getCases()));
        globalRecovered.setText(numberFormat.format(worldStats.getRecovered()));
        globalDeaths.setText(numberFormat.format(worldStats.getDeaths()));
        nepCases.setText(numberFormat.format(nepalStats.getTotal()));
        nepRecovered.setText(numberFormat.format(nepalStats.getRecovered()));
        nepDeaths.setText(numberFormat.format(nepalStats.getDeath()));

        provinceDetails.setOnClickListener(this);
        districtDetails.setOnClickListener(this);
        municipalityDetails.setOnClickListener(this);
        genderDetails.setOnClickListener(this);
        ageGroupDetails.setOnClickListener(this);
        worldStatCard.setOnClickListener(this);
        nepalStatCard.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.provinceInfo_text:
                //Insert activity to display province info
                Log.d(TAG, "onClick: Province Clicked");
                break;
            case R.id.districtInfo_text:
                //Activity for district info
                Log.d(TAG, "onClick: District Clicked");
                break;
            case R.id.municipalityInfo_text:
                //Activity for municipality info
                Log.d(TAG, "onClick: Municipality Clicked");
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Municipality Details are currently unavailable!")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).create().show();
                break;
            case R.id.genderInfo_text:
                //Activity for gender info
                Log.d(TAG, "onClick: Gender Clicked");
                break;
            case R.id.ageGroup_text:
                //Activity for age group info
                Log.d(TAG, "onClick: Age Group Clicked");
                break;
            case R.id.worldStatsCard:
                //Activity for world stats
                Log.d(TAG, "onClick: World Stats Clicked");
                break;
            case R.id.nepalStatsCard:
                //Activity for Nepal stats
                Log.d(TAG, "onClick: Nepal Stats Clicked");
                break;
        }
    }
}