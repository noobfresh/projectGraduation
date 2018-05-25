package com.cqu.project.graduation.entity;

import com.sun.scenario.effect.impl.prism.PrImage;

public class ODvo {

    private String startStation;

    private String endStation;

    private String c;

    public ODvo(String startStation, String endStation, String c) {
        this.startStation = startStation;
        this.endStation = endStation;
        this.c = c;
    }

    public ODvo() {
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }
}
