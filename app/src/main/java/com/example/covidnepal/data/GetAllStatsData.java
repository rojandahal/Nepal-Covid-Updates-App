package com.example.covidnepal.data;

import com.example.covidnepal.model.AgeGroupInfo;
import com.example.covidnepal.model.DistrictInfo;
import com.example.covidnepal.model.GenderInfo;
import com.example.covidnepal.model.MunicipalityInfo;
import com.example.covidnepal.model.ProvinceInfo;

import java.util.HashMap;
import java.util.Map;

public class GetAllStatsData {

    private String TAG = "GetAllStatsData";

    /**
     * Private static instance declared
     */
    private static GetAllStatsData worldStatInstance;

    /**
     * Private static instance of Municipality info Map which contains all the data of the
     * municipalities
     */
    private static Map<Integer,MunicipalityInfo> municipalityInfoMap;

    private static Map<Integer, ProvinceInfo> provinceInfoMap;

    private static Map<String, GenderInfo> genderInfoMap;

    private static Map<String, AgeGroupInfo> ageGroupInfoMap;

    /**
     * Getting static instance
     */

    private static Map<Integer, DistrictInfo> districtInfoMap;

    public static synchronized GetAllStatsData getInstance() {
        if (worldStatInstance == null) {
            worldStatInstance = new GetAllStatsData();
            return worldStatInstance;
        }
        return worldStatInstance;
    }


    public void getWorldStatsDataFromAPI() {
        new FetchDataFromAPI().getWorldStats();
    }

    public void getDistrictInfoMap() {
        new FetchDataFromAPI().getDistrictInfo();
    }

    public Map<Integer, DistrictInfo> districtInstance() {
        if (districtInfoMap == null) {
            districtInfoMap = new HashMap<>();
        }
        return districtInfoMap;
    }

    public void getMunicipalityInfoMap(){
        new FetchDataFromAPI().getMunicipalityInfo();
    }

    public Map<Integer,MunicipalityInfo> municipalityInstance(){
        if(municipalityInfoMap == null){
            municipalityInfoMap = new HashMap<>();
        }
        return municipalityInfoMap;
    }

    public void getNepalStats(){
        new FetchDataFromAPI().getNepalStats();
    }

    public Map<Integer,ProvinceInfo> provinceInstance(){

        if(provinceInfoMap == null){
            provinceInfoMap = new HashMap<>();
        }
        return provinceInfoMap;
    }

    public void getProvinceCases(){
        new FetchDataFromAPI().getProvinceCasesData();
    }

    public void getDistrictCases(){
        new FetchDataFromAPI().getDistrictCasesData();
    }

    public void getMunicipalityCases(){
        new FetchDataFromAPI().getMunicipalityCasesData();
    }

    public Map<String,GenderInfo> genderInstance(){
        if(genderInfoMap == null)
            genderInfoMap = new HashMap<>();
        return genderInfoMap;
    }

    public void getGenderCasesData(){
        new FetchDataFromAPI().getGenderData();
    }

    public Map<String,AgeGroupInfo> ageGroupInstance(){
        if (ageGroupInfoMap == null)
            ageGroupInfoMap = new HashMap<>();
        return ageGroupInfoMap;
    }

    public void getAgeGroupCasesData(){
        new FetchDataFromAPI().getAgeGroupInfo();
    }

}
