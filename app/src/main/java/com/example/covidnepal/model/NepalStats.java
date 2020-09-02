package com.example.covidnepal.model;

public class NepalStats {

    private static NepalStats INSTANCE;

    private int total;
    private int active;
    private int death;
    private int recovered;

    public static synchronized NepalStats getINSTANCE() {
        if(INSTANCE == null)
            INSTANCE = new NepalStats();

        return INSTANCE;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getDeath() {
        return death;
    }

    public void setDeath(int death) {
        this.death = death;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }
}
