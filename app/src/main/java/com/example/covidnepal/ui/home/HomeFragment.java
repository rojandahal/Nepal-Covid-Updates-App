package com.example.covidnepal.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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
import com.example.covidnepal.SecondActivity;
import com.example.covidnepal.model.NepalStats;
import com.example.covidnepal.model.WorldStats;
import com.example.covidnepal.ui.agegroupinfo.AgeGroupDetails;
import com.example.covidnepal.ui.districtdetails.DistrictDetailsActivity;
import com.example.covidnepal.ui.genderinfo.GenderInfoDetails;
import com.example.covidnepal.ui.nepaldetails.NepalDetailsActivity;
import com.example.covidnepal.ui.provincedetails.ProvinceActivityDetails;
import com.example.covidnepal.ui.worlddetails.WorldDetailsActivity;

import java.text.NumberFormat;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "Home Fragment";

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

    private TextView number1;
    private TextView number2;
    private TextView number3;
    private TextView number4;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        initializeViews(root);
        setTextViews();

        provinceDetails.setOnClickListener(this);
        districtDetails.setOnClickListener(this);
        municipalityDetails.setOnClickListener(this);
        genderDetails.setOnClickListener(this);
        ageGroupDetails.setOnClickListener(this);
        worldStatCard.setOnClickListener(this);
        nepalStatCard.setOnClickListener(this);
        number1.setOnClickListener(this);
        number2.setOnClickListener(this);
        number3.setOnClickListener(this);
        number4.setOnClickListener(this);

        return root;
    }

    private void setTextViews(){
        globalCases.setText(numberFormat.format(worldStats.getCases()));
        globalRecovered.setText(numberFormat.format(worldStats.getRecovered()));
        globalDeaths.setText(numberFormat.format(worldStats.getDeaths()));
        nepCases.setText(numberFormat.format(nepalStats.getCases()));
        nepRecovered.setText(numberFormat.format(nepalStats.getRecovered()));
        nepDeaths.setText(numberFormat.format(nepalStats.getDeath()));
    }

    private void initializeViews(View root){
        //Global details
        globalCases = root.findViewById(R.id.world_cases_number);
        globalRecovered = root.findViewById(R.id.world_recovered_number);
        globalDeaths = root.findViewById(R.id.world_deaths_number);
        //Nepal details
        nepCases = root.findViewById(R.id.nepal_cases_number);
        nepRecovered = root.findViewById(R.id.nepal_recovered_number);
        nepDeaths = root.findViewById(R.id.nepal_deaths_number);
        //Info dashboard
        provinceDetails = root.findViewById(R.id.provinceInfo_text);
        districtDetails = root.findViewById(R.id.districtInfo_text);
        municipalityDetails = root.findViewById(R.id.municipalityInfo_text);
        genderDetails = root.findViewById(R.id.genderInfo_text);
        ageGroupDetails = root.findViewById(R.id.ageGroup_text);
        worldStatCard = root.findViewById(R.id.worldStatsCard);
        nepalStatCard = root.findViewById(R.id.nepalStatsCard);
        number1 = root.findViewById(R.id.number1);
        number2 = root.findViewById(R.id.number2);
        number3 = root.findViewById(R.id.number3);
        number4 = root.findViewById(R.id.number4);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.provinceInfo_text:
                //Insert activity to display province info
                Log.d(TAG, "onClick: Province Clicked");
                if(internetConnectionError())
                    break;
                startActivity(new Intent(getActivity(), ProvinceActivityDetails.class));
                break;
            case R.id.districtInfo_text:
                //Activity for district info
                Log.d(TAG, "onClick: District Clicked");
                if(internetConnectionError())
                    break;
                startActivity(new Intent(getActivity(), DistrictDetailsActivity.class));
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
                if(internetConnectionError())
                    break;
                startActivity(new Intent(getActivity(), GenderInfoDetails.class));
                break;
            case R.id.ageGroup_text:
                //Activity for age group info
                Log.d(TAG, "onClick: Age Group Clicked");
                if(internetConnectionError())
                    break;
                startActivity(new Intent(getActivity(), AgeGroupDetails.class));
                break;
            case R.id.worldStatsCard:
                //Activity for world stats
                Log.d(TAG, "onClick: World Stats Clicked");
                startActivity(new Intent(getActivity(), WorldDetailsActivity.class));
                break;
            case R.id.nepalStatsCard:
                //Activity for Nepal stats
                Log.d(TAG, "onClick: Nepal Stats Clicked");
                startActivity(new Intent(getActivity(), NepalDetailsActivity.class));
                break;

            case R.id.number1:
                startDialIntent("9851-255-834");
                break;
            case R.id.number2:
                startDialIntent("9851-255-837");
                break;
            case R.id.number3:
                startDialIntent("9851-255-839");
                break;
            case R.id.number4:
                startDialIntent("1115");
                break;
        }
    }

    private void startDialIntent(String s) {
        Intent dialIntent = new Intent(Intent.ACTION_DIAL);
        String num = "tel:" + s;
        dialIntent.setData(Uri.parse(num));
        startActivity(dialIntent);
    }

    private boolean internetConnectionError(){

        if(worldStats.getCases()==0 && nepalStats.getCases() == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("District Details Unavailable! \n" +
                    "Check Internet Connection and Update Data")
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).setNegativeButton(null, null)
                    .create().show();
            return true;
        }
        return false;
    }
}