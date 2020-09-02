package com.example.covidnepal.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.covidnepal.controller.StatsController;
import com.example.covidnepal.model.AgeGroupInfo;
import com.example.covidnepal.model.DistrictInfo;
import com.example.covidnepal.model.GenderInfo;
import com.example.covidnepal.model.MunicipalityInfo;
import com.example.covidnepal.model.NepalStats;
import com.example.covidnepal.model.ProvinceInfo;
import com.example.covidnepal.model.WorldStats;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FetchDataFromAPI {

    private String TAG = "FetchFromAPI";
    private Map<String, String> urlObject = new HashMap<>();

    GetAllStatsData allData = GetAllStatsData.getInstance();

    public FetchDataFromAPI() {
        urlObject.put("worldStatsURL", "https://data.nepalcorona.info/api/v1/world");
        urlObject.put("nepalStatsURL", "https://data.nepalcorona.info/api/v1/covid/summary");
        urlObject.put("districtInfo", "https://data.nepalcorona.info/api/v1/districts");
        urlObject.put("municipalityInfo", "https://data.nepalcorona.info/api/v1/municipals");
        urlObject.put("provinceInfo", "https://data.nepalcorona.info/api/v1/covid/summary");
    }

    public void getWorldStats() {

        final WorldStats worldStats = WorldStats.getInstance();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, urlObject.get("worldStatsURL"), (JSONObject) null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                try {
//                                    Log.d(TAG, "onResponse: Cases: " + response.get("cases"));
//                                    Log.d(TAG, "onResponse: Active: " + response.get("active"));
//                                    Log.d(TAG, "onResponse: Deaths: " + response.get("deaths"));
//                                    Log.d(TAG, "onResponse: Tests: " + response.get("tests"));

                                    worldStats.setCases(Long.parseLong(String.valueOf(response.get("cases"))));
                                    worldStats.setTodayCases(Long.parseLong(String.valueOf(response.get("todayCases"))));
                                    worldStats.setActive(Long.parseLong(String.valueOf(response.get("active"))));
                                    worldStats.setDeaths(Long.parseLong(String.valueOf(response.get("deaths"))));
                                    worldStats.setTodayDeaths(Long.parseLong(String.valueOf(response.get("todayDeaths"))));
                                    worldStats.setRecovered(Long.parseLong(String.valueOf(response.get("recovered"))));
                                    worldStats.setTodayRecovered(Long.parseLong(String.valueOf(response.get("todayRecovered"))));
                                    worldStats.setCritical(Long.parseLong(String.valueOf(response.get("critical"))));
                                    worldStats.setAffectedCountries(Long.parseLong(String.valueOf(response.get("affectedCountries"))));
                                    worldStats.setTestsDone(Long.parseLong(String.valueOf(response.get("tests"))));

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        StatsController.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    public void getNepalStats() {
        final NepalStats nepalStats = NepalStats.getINSTANCE();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, urlObject.get("nepalStatsURL"), (JSONObject) null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                try {

                                    Log.d(TAG, "onNepalResponse: Total: " + response.get("total"));
                                    JSONArray currentState = response.getJSONArray("current_state");

                                    nepalStats.setTotal(Integer.parseInt(String.valueOf(response.get("total"))));
                                    JSONObject deathStats = currentState.getJSONObject(0);
                                    nepalStats.setDeath(Integer.parseInt(String.valueOf(deathStats.get("count"))));

                                    JSONObject activeStats = currentState.getJSONObject(1);
                                    nepalStats.setActive(Integer.parseInt(String.valueOf(activeStats.get("count"))));

                                    JSONObject recoveredStat = currentState.getJSONObject(2);
                                    nepalStats.setRecovered(Integer.parseInt(String.valueOf(recoveredStat.get("count"))));

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        StatsController.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    public void getDistrictInfo() {
        final Map<Integer, DistrictInfo> districtInfoMap = allData.districtInstance();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                urlObject.get("districtInfo"), (JSONArray) null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            for (int i = 0; i < response.length(); i++) {
                                DistrictInfo districtInfo = new DistrictInfo();

                                JSONObject responseObject = response.getJSONObject(i);
                                int id = Integer.parseInt(String.valueOf(responseObject.get("id")));
                                districtInfo.setId(id);
                                districtInfo.setTitle(String.valueOf(responseObject.get("title_en")));

                                JSONObject centroid = responseObject.getJSONObject("centroid");
                                JSONArray coordinates = centroid.getJSONArray("coordinates");

                                districtInfo.setLongitude(Double.parseDouble(String.valueOf(coordinates.get(0))));
                                districtInfo.setLatitude(Double.parseDouble(String.valueOf(coordinates.get(1))));

//                                String title = String.valueOf(responseObject.get("title_en"));
//                                double longitude = Double.parseDouble(String.valueOf(coordinates.get(0)));
//                                Log.d(TAG, "onResponse District: ID: " + id + " " +
//                                        title + " " +
//                                        longitude);
                                districtInfoMap.put(id, districtInfo);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        StatsController.getInstance().addToRequestQueue(jsonArrayRequest);
    }

    public void getMunicipalityInfo() {
        final Map<Integer, MunicipalityInfo> municipalityInfoMap = allData.municipalityInstance();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                urlObject.get("municipalityInfo"), (JSONArray) null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            for (int i = 0; i < response.length(); i++) {
                                MunicipalityInfo municipalityInfo = new MunicipalityInfo();

                                JSONObject responseObject = response.getJSONObject(i);
                                int id = Integer.parseInt(String.valueOf(responseObject.get("id")));
                                municipalityInfo.setId(id);
                                municipalityInfo.setTitle(String.valueOf(responseObject.get("title_en")));

                                JSONObject centroid = responseObject.getJSONObject("centroid");
                                JSONArray coordinates = centroid.getJSONArray("coordinates");

                                municipalityInfo.setLongitude(Double.parseDouble(String.valueOf(coordinates.get(0))));
                                municipalityInfo.setLatitude(Double.parseDouble(String.valueOf(coordinates.get(1))));

//                                String title = String.valueOf(responseObject.get("title_en"));
//                                double longitude = Double.parseDouble(String.valueOf(coordinates.get(0)));
//                                Log.d(TAG, "onResponse Municipality: ID: " + id + " " +
//                                        title + " " +
//                                        longitude);

                                municipalityInfoMap.put(id, municipalityInfo);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        StatsController.getInstance().addToRequestQueue(jsonArrayRequest);
    }

    public void getProvinceCasesData() {
        final Map<Integer, ProvinceInfo> provinceInfoMap = allData.provinceInstance();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, urlObject.get("provinceInfo"), (JSONObject) null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                try {
                                    /*
                                    Getting province Cases
                                     */
                                    JSONObject province = response.getJSONObject("province");
                                    //Cases
                                    JSONArray cases = province.getJSONArray("cases");
                                    for (int i = 0; i < cases.length(); i++) {
                                        ProvinceInfo provinceInfo = new ProvinceInfo();
                                        int prov = Integer.parseInt(String.valueOf(cases.getJSONObject(i).get("province")));
                                        provinceInfo.setId(prov);
                                        provinceInfo.setCases(Integer.parseInt(String.valueOf(cases.getJSONObject(i).get("count"))));
                                        provinceInfoMap.put(prov, provinceInfo);
                                    }

                                    //Recovered
                                    JSONArray activeObject = province.getJSONArray("active");
                                    for (int i = 0; i < activeObject.length(); i++) {
                                        int prov = Integer.parseInt(String.valueOf(activeObject.getJSONObject(i).get("province")));
                                        if (provinceInfoMap.containsKey(prov)) {
                                            provinceInfoMap.get(prov)
                                                    .setActive(Integer.parseInt(String.valueOf(activeObject.getJSONObject(i).get("count"))));
                                        }
                                    }


                                    //Recovered
                                    JSONArray recovered = province.getJSONArray("recovered");
                                    for (int i = 0; i < recovered.length(); i++) {
                                        int prov = Integer.parseInt(String.valueOf(recovered.getJSONObject(i).get("province")));
                                        if (provinceInfoMap.containsKey(prov)) {
                                            provinceInfoMap.get(prov)
                                                    .setRecovered(Integer.parseInt(String.valueOf(recovered.getJSONObject(i).get("count"))));
                                        }
                                    }

                                    //Deaths
                                    JSONArray deaths = province.getJSONArray("deaths");
                                    for (int i = 0; i < deaths.length(); i++) {
                                        int prov = Integer.parseInt(String.valueOf(deaths.getJSONObject(i).get("province")));
                                        if (provinceInfoMap.containsKey(prov)) {
                                            provinceInfoMap.get(prov)
                                                    .setDeaths(Integer.parseInt(String.valueOf(deaths.getJSONObject(i).get("count"))));
                                        }
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        StatsController.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    public void getDistrictCasesData() {
        final Map<Integer, DistrictInfo> districtInfoMap = allData.districtInstance();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, urlObject.get("provinceInfo"), (JSONObject) null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                try {
                                    /*
                                    Getting district data
                                     */
                                    JSONObject district = response.getJSONObject("district");
                                    //Cases
                                    JSONArray districtCases = district.getJSONArray("cases");
                                    for (int i = 0; i < districtCases.length(); i++) {
                                        JSONObject dObject = districtCases.getJSONObject(i);
                                        int dist = Integer.parseInt(String.valueOf(dObject.get("district")));

                                        if (districtInfoMap.containsKey(dist)) {
                                            districtInfoMap.get(dist)
                                                    .setCases(Integer.parseInt(String.valueOf(dObject.get("count"))));
                                        }
                                    }

                                    //Active
                                    JSONArray districtActive = district.getJSONArray("active");
                                    for (int i = 0; i < districtActive.length(); i++) {
                                        JSONObject dObject = districtActive.getJSONObject(i);
                                        int dist = Integer.parseInt(String.valueOf(dObject.get("district")));

                                        if (districtInfoMap.containsKey(dist)) {
                                            districtInfoMap.get(dist)
                                                    .setActive(Integer.parseInt(String.valueOf(dObject.get("count"))));
                                        }
                                    }

                                    //Recovered
                                    JSONArray districtRecovered = district.getJSONArray("recovered");
                                    for (int i = 0; i < districtRecovered.length(); i++) {
                                        JSONObject dObject = districtRecovered.getJSONObject(i);
                                        int dist = Integer.parseInt(String.valueOf(dObject.get("district")));

                                        if (districtInfoMap.containsKey(dist)) {
                                            districtInfoMap.get(dist)
                                                    .setRecovered(Integer.parseInt(String.valueOf(dObject.get("count"))));
                                        }
                                    }

                                    //Deaths
                                    JSONArray districtDeaths = district.getJSONArray("deaths");
                                    for (int i = 0; i < districtDeaths.length(); i++) {
                                        JSONObject dObject = districtDeaths.getJSONObject(i);
                                        int dist = Integer.parseInt(String.valueOf(dObject.get("district")));

                                        if (districtInfoMap.containsKey(dist)) {
                                            districtInfoMap.get(dist)
                                                    .setDeaths(Integer.parseInt(String.valueOf(dObject.get("count"))));
                                        }
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        StatsController.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    public void getMunicipalityCasesData() {

        final Map<Integer, MunicipalityInfo> municipalityInfoMap = allData.municipalityInstance();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, urlObject.get("provinceInfo"), (JSONObject) null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                try {
                                    /*
                                    Getting Municipality data
                                     */
                                    JSONObject municipality = response.getJSONObject("municipality");
                                    //Cases
                                    JSONArray mCases = municipality.getJSONArray("cases");
                                    for (int i = 0; i < mCases.length(); i++) {
                                        JSONObject dObject = mCases.getJSONObject(i);
                                        int munID = Integer.parseInt(String.valueOf(dObject.get("municipality")));

                                        if (municipalityInfoMap.containsKey(munID)) {
                                            municipalityInfoMap.get(munID)
                                                    .setCases(Integer.parseInt(String.valueOf(dObject.get("count"))));
                                        }
                                    }

                                    //Active
                                    JSONArray mActive = municipality.getJSONArray("active");
                                    for (int i = 0; i < mActive.length(); i++) {
                                        JSONObject dObject = mActive.getJSONObject(i);
                                        int munID = Integer.parseInt(String.valueOf(dObject.get("municipality")));

                                        if (municipalityInfoMap.containsKey(munID)) {
                                            municipalityInfoMap.get(munID)
                                                    .setActive(Integer.parseInt(String.valueOf(dObject.get("count"))));
                                        }
                                    }

                                    //Recovered
                                    JSONArray mRecover = municipality.getJSONArray("recovered");
                                    for (int i = 0; i < mRecover.length(); i++) {
                                        JSONObject dObject = mRecover.getJSONObject(i);
                                        int munID = Integer.parseInt(String.valueOf(dObject.get("municipality")));

                                        if (municipalityInfoMap.containsKey(munID)) {
                                            municipalityInfoMap.get(munID)
                                                    .setRecovered(Integer.parseInt(String.valueOf(dObject.get("count"))));
                                        }
                                    }

                                    //Deaths
                                    JSONArray mDeath = municipality.getJSONArray("deaths");
                                    for (int i = 0; i < mDeath.length(); i++) {
                                        JSONObject dObject = mDeath.getJSONObject(i);
                                        int munID = Integer.parseInt(String.valueOf(dObject.get("municipality")));

                                        if (municipalityInfoMap.containsKey(munID)) {
                                            municipalityInfoMap.get(munID)
                                                    .setDeaths(Integer.parseInt(String.valueOf(dObject.get("count"))));
                                        }
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        StatsController.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    public void getGenderData() {
        final Map<String, GenderInfo> genderInfoMap = allData.genderInstance();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, urlObject.get("provinceInfo"), (JSONObject) null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                try {
                                    /*
                                    Getting Municipality data
                                     */
                                    JSONObject gender1 = response.getJSONObject("gender");
                                    //Cases
                                    JSONArray gCases = gender1.getJSONArray("cases");
                                    for (int i = 0; i < gCases.length(); i++) {
                                        JSONObject object = gCases.getJSONObject(i);
                                        GenderInfo info = new GenderInfo();
                                        String gender = object.getString("gender").toLowerCase();
                                        if (genderInfoMap.containsKey(gender)) {
                                            int prev = genderInfoMap.get(gender).getCases();
                                            genderInfoMap.get(gender)
                                                    .setCases(prev +
                                                            Integer.parseInt(String.valueOf(object.get("count"))));
                                            continue;
                                        }
                                        info.setGender(gender);
                                        info.setCases(Integer.parseInt(String.valueOf(object.get("count"))));
                                        genderInfoMap.put(gender, info);
                                    }

                                    //Active
                                    JSONArray gActive = gender1.getJSONArray("active");
                                    for (int i = 0; i < gActive.length(); i++) {
                                        JSONObject object = gActive.getJSONObject(i);
                                        String gender = object.getString("gender");
                                        if (genderInfoMap.containsKey(gender)) {
                                            genderInfoMap.get(gender)
                                                    .setActive(Integer.parseInt(String.valueOf(object.get("count"))));
                                        }
                                    }

                                    //Recovered
                                    JSONArray gRecovered = gender1.getJSONArray("recovered");
                                    for (int i = 0; i < gRecovered.length(); i++) {
                                        JSONObject object = gRecovered.getJSONObject(i);
                                        String gender = object.getString("gender").toLowerCase();
                                        if (i == 2 || i == 3) {
                                            if (genderInfoMap.containsKey(gender)) {
                                                genderInfoMap.get(gender)
                                                        .setRecovered(genderInfoMap.get(gender).getRecovered() +
                                                                Integer.parseInt(String.valueOf(object.get("count"))));
                                                continue;
                                            }

                                        }
                                        if (genderInfoMap.containsKey(gender)) {
                                            genderInfoMap.get(gender)
                                                    .setRecovered(Integer.parseInt(String.valueOf(object.get("count"))));
                                        }
                                    }

                                    //Deaths
                                    JSONArray gDeaths = gender1.getJSONArray("deaths");
                                    for (int i = 0; i < gDeaths.length(); i++) {
                                        JSONObject object = gDeaths.getJSONObject(i);
                                        String gender = object.getString("gender");
                                        if (genderInfoMap.containsKey(gender)) {
                                            genderInfoMap.get(gender)
                                                    .setDeaths(Integer.parseInt(String.valueOf(object.get("count"))));
                                        }
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        StatsController.getInstance().addToRequestQueue(jsonObjectRequest);

    }

    public void getAgeGroupInfo() {
        final Map<String, AgeGroupInfo> ageGroupInfoMap = allData.ageGroupInstance();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, urlObject.get("provinceInfo"), (JSONObject) null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                try {
                                    /*
                                    Getting Municipality data
                                     */
                                    JSONObject gender1 = response.getJSONObject("age_group");
                                    //Cases
                                    JSONArray ageCases = gender1.getJSONArray("cases");
                                    for(int i=0; i<ageCases.length(); i++){
                                        AgeGroupInfo ageGroupInfo = new AgeGroupInfo();
                                        JSONObject object = ageCases.getJSONObject(i);
                                        String age_group = object.getString("age");
                                        ageGroupInfo.setAgeGroup(age_group);
                                        ageGroupInfo.setCases(Integer.parseInt(String.valueOf(object.get("count"))));
                                        ageGroupInfoMap.put(age_group,ageGroupInfo);
                                    }

                                    //Active
                                    JSONArray ageActive = gender1.getJSONArray("active");
                                    for(int i=0; i<ageActive.length(); i++){
                                        JSONObject object = ageActive.getJSONObject(i);
                                        String age_group = object.getString("age");
                                        if(ageGroupInfoMap.containsKey(age_group)){
                                            ageGroupInfoMap.get(age_group)
                                                    .setActive(Integer.parseInt(String.valueOf(object.get("count"))));
                                        }
                                    }

                                    //Recovered
                                    JSONArray ageRecovered = gender1.getJSONArray("recovered");
                                    for(int i=0; i<ageRecovered.length(); i++){
                                        JSONObject object = ageRecovered.getJSONObject(i);
                                        String age_group = object.getString("age");
                                        if(ageGroupInfoMap.containsKey(age_group)){
                                            ageGroupInfoMap.get(age_group)
                                                    .setRecovered(Integer.parseInt(String.valueOf(object.get("count"))));
                                        }
                                    }

                                    //Deaths
                                    JSONArray ageDeaths = gender1.getJSONArray("deaths");
                                    for(int i=0; i<ageDeaths.length(); i++){
                                        JSONObject object = ageDeaths.getJSONObject(i);
                                        String age_group = object.getString("age");
                                        if(ageGroupInfoMap.containsKey(age_group)){
                                            ageGroupInfoMap.get(age_group)
                                                    .setDeaths(Integer.parseInt(String.valueOf(object.get("count"))));
                                        }
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        StatsController.getInstance().addToRequestQueue(jsonObjectRequest);
    }


}
