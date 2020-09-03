package com.example.covidnepal.model;

public class NepalStats {

    private static NepalStats INSTANCE;

    private int cases;
    private int active;
    private int death;
    private int recovered;

    private int tested_total;
    private int tested_negative;
    private int tested_rdt;
    private int quarantined;
    private int in_isolation;

    public static synchronized NepalStats getINSTANCE() {
        if(INSTANCE == null)
            INSTANCE = new NepalStats();

        return INSTANCE;
    }

    public int getTested_negative() {
        return tested_negative;
    }

    public void setTested_negative(int tested_negative) {
        this.tested_negative = tested_negative;
    }

    public int getTested_rdt() {
        return tested_rdt;
    }

    public void setTested_rdt(int tested_rdt) {
        this.tested_rdt = tested_rdt;
    }

    public int getQuarantined() {
        return quarantined;
    }

    public void setQuarantined(int quarantined) {
        this.quarantined = quarantined;
    }

    public int getIn_isolation() {
        return in_isolation;
    }

    public void setIn_isolation(int in_isolation) {
        this.in_isolation = in_isolation;
    }

    public int getTested_total() {
        return tested_total;
    }

    public void setTested_total(int tested_total) {
        this.tested_total = tested_total;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
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
