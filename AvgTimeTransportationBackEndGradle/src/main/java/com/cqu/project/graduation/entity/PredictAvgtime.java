package com.cqu.project.graduation.entity;

public class PredictAvgtime {
    private String startStation;

    private String endStation;

    private Integer duration;

    private String time;

    private Integer predictDuration;

    private String date;

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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    public Integer getPredictDuration() {
        return predictDuration;
    }

    public void setPredictDuration(Integer predictDuration) {
        this.predictDuration = predictDuration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }
}