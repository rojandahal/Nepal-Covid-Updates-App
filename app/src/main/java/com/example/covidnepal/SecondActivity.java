package com.example.covidnepal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.covidnepal.data.GetAllStatsData;
import com.example.covidnepal.model.AgeGroupInfo;
import com.example.covidnepal.model.DistrictInfo;
import com.example.covidnepal.model.GenderInfo;
import com.example.covidnepal.model.MunicipalityInfo;
import com.example.covidnepal.model.NepalStats;
import com.example.covidnepal.model.ProvinceInfo;
import com.example.covidnepal.model.WorldStats;

import java.util.Map;

public class SecondActivity extends AppCompatActivity {

    private String TAG = "SecondActivity";
    private GetAllStatsData allData = GetAllStatsData.getInstance();
    private Map<Integer, DistrictInfo> districtInfoMap = allData.districtInstance();
    private Map<Integer, MunicipalityInfo> municipalityInfoMap = allData.municipalityInstance();
    private NepalStats nepalStats = NepalStats.getINSTANCE();
    private Map<Integer, ProvinceInfo> provinceInfoMap = allData.provinceInstance();
    private Map<String, GenderInfo> genderInfoMap = allData.genderInstance();
    private Map<Integer , AgeGroupInfo> ageGroupInfoMap = allData.ageGroupInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        /*
        WorldStats Log
         */
        WorldStats worldStatsData = WorldStats.getInstance();
        Log.d(TAG, "onCreate: Cases " + worldStatsData.getCases());
        Log.d(TAG, "onCreate: Active " + worldStatsData.getActive());
        Log.d(TAG, "onCreate: Deaths " + worldStatsData.getDeaths());
        Log.d(TAG, "onCreate: Tests " + worldStatsData.getTestsDone());
        Log.d(TAG, "onCreate: todayCases " + worldStatsData.getTodayCases());
        Log.d(TAG, "onCreate: todayDeaths " + worldStatsData.getTodayDeaths());
        Log.d(TAG, "onCreate: critical " + worldStatsData.getCritical());
        Log.d(TAG, "onCreate: Affected Countries " + worldStatsData.getAffectedCountries());

        /*
        Nepal Stats Log
         */

        Log.d(TAG, "onCreateNepalStats: Cases " + nepalStats.getCases());
        Log.d(TAG, "onCreateNepalStats:: Active " + nepalStats.getActive());
        Log.d(TAG, "onCreateNepalStats: Recovered " + nepalStats.getRecovered());
        Log.d(TAG, "onCreateNepalStats: Deaths " + nepalStats.getDeath());

        /*
        District Info
         */
        //districtInfoIterator();
//        districtCheckCases(48);
//        districtCheckCases(13);
//        districtCheckCases(482);

        /*
        Municipality Info Not Available currently
         */
        //municipalityInfoIterator();

        /*
        Province Iterator
         */
        //provinceInfoIterator();

        /*
        Gender Iterator
         */
        //genderInfoIterator();

        /*
        AgeGroup Iterator
         */
        ageGroupInfoIterator();
    }

    private void ageGroupInfoIterator() {
        for(int i = 0; i<ageGroupInfoMap.size(); i++){
            AgeGroupInfo ageGroup = ageGroupInfoMap.get(i);
            assert ageGroup != null;
            Log.d(TAG, String.format("Age Group Info: %s %d %d %d %d",
                    ageGroup.getAgeGroup(), ageGroup.getCases(),
                    ageGroup.getActive(), ageGroup.getRecovered(), ageGroup.getDeaths()));
        }
    }

    private void genderInfoIterator() {
        for(Map.Entry entry: genderInfoMap.entrySet()){
            GenderInfo gender = genderInfoMap.get(entry.getKey());
            assert gender != null;
            Log.d(TAG, String.format("Gender INFO:: %s %d %d %d %d",
                    gender.getGender(), gender.getCases(), gender.getActive(), gender.getRecovered(), gender.getDeaths()));
        }
    }

    private void districtCheckCases(int id) {

        DistrictInfo info = districtInfoMap.get(id);
        Log.d(TAG, String.format("DistrictINFO: ID: %d %s %s %s %d %d %d %d",
                info.getId(), info.getTitle(), info.getLatitude(), info.getLongitude(),
                info.getCases(), info.getActive(), info.getRecovered(), info.getDeaths()));
    }

    private void provinceInfoIterator() {
        for(Map.Entry entry : provinceInfoMap.entrySet()){
            ProvinceInfo pInfo = provinceInfoMap.get(entry.getKey());
            assert pInfo != null;
            Log.d(TAG, String.format("ProvinceINFO:: %d %d %d %d %d",
                    pInfo.getId(), pInfo.getCases(), pInfo.getActive(), pInfo.getRecovered(), pInfo.getDeaths()));
        }
    }

    private void municipalityInfoIterator() {
        for(Map.Entry entry : municipalityInfoMap.entrySet()){
            MunicipalityInfo mInfo = municipalityInfoMap.get(entry.getKey());
            assert mInfo != null;
            Log.d(TAG, String.format("MunicipalityINFO:: %d %s %s %s %d %d %d %d",
                    mInfo.getId(), mInfo.getTitle(), mInfo.getLatitude(), mInfo.getLongitude(),
                    mInfo.getCases(), mInfo.getActive(), mInfo.getRecovered(), mInfo.getDeaths()));
        }
    }

    private void districtInfoIterator() {
        for (Map.Entry entry : districtInfoMap.entrySet()) {
            DistrictInfo info = districtInfoMap.get(entry.getKey());
            assert info != null;
            Log.d(TAG, String.format("DistrictINFO: ID: %d %s %s %s %d %d %d %d",
                    info.getId(), info.getTitle(), info.getLatitude(), info.getLongitude(),
                    info.getCases(), info.getActive(), info.getRecovered(), info.getDeaths()));
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}