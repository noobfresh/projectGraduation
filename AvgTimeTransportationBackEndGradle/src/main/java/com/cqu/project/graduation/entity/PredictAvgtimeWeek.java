package com.cqu.project.graduation.entity;

public class PredictAvgtimeWeek {
    private String startStation;

    private String endStation;

    private String date;

    private Integer trueDuration;

    private Integer predictDuration;

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation == null ? null : startStation.trim();
    }

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation == null ? null : endStation.trim();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    public Integer getTrueDuration() {
        return trueDuration;
    }

    public void setTrueDuration(Integer trueDuration) {
        this.trueDuration = trueDuration;
    }

    public Integer getPredictDuration() {
        return predictDuration;
    }

    public void setPredictDuration(Integer predictDuration) {
        this.predictDuration = predictDuration;
    }
}