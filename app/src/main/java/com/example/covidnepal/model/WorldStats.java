package com.example.covidnepal.model;


public class WorldStats {

    private static WorldStats worldStatInstance;

    private long cases ;
    private long todayCases ;
    private long deaths;
    private long todayDeaths ;
    private long recovered;
    private long todayRecovered ;
    private long active;
    private long critical ;
    private long testsDone;
    private long affectedCountries;

    public static synchronized WorldStats getInstance(){

        if(worldStatInstance== null){
            worldStatInstance = new WorldStats();
        }
        return worldStatInstance;
    }

    public WorldStats() {
    }

    public long getCases() {
        return cases;
    }

    public void setCases(long cases) {
        this.cases = cases;
    }

    public long getTodayCases() {
        return todayCases;
    }

    public void setTodayCases(long todayCases) {
        this.todayCases = todayCases;
    }

    public long getDeaths() {
        return deaths;
    }

    public void setDeaths(long deaths) {
        this.deaths = deaths;
    }

    public long getTodayDeaths() {
        return todayDeaths;
    }

    public void setTodayDeaths(long todayDeaths) {
        this.todayDeaths = todayDeaths;
    }

    public long getRecovered() {
        return recovered;
    }

    public void setRecovered(long recovered) {
        this.recovered = recovered;
    }

    public long getTodayRecovered() {
        return todayRecovered;
    }

    public void setTodayRecovered(long todayRecovered) {
        this.todayRecovered = todayRecovered;
    }

    public long getActive() {
        return active;
    }

    public void setActive(long active) {
        this.active = active;
    }

    public long getCritical() {
        return critical;
    }

    public void setCritical(long critical) {
        this.critical = critical;
    }

    public long getTestsDone() {
        return testsDone;
    }

    public void setTestsDone(long testsDone) {
        this.testsDone = testsDone;
    }

    public long getAffectedCountries() {
        return affectedCountries;
    }

    public void setAffectedCountries(long affectedCountries) {
        this.affectedCountries = affectedCountries;
    }
}
