package com.cqu.project.graduation.entity;

public class PredictBusDay {
    private String lineNo;

    private String startStation;

    private String endStation;

    private String date;

    private String peroid;

    private Integer trueDuration;

    private Integer predictDuration;

    public String getLineNo() {
        return lineNo;
    }

    public void setLineNo(String lineNo) {
        this.lineNo = lineNo == null ? null : lineNo.trim();
    }

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

    public String getPeroid() {
        return peroid;
    }

    public void setPeroid(String peroid) {
        this.peroid = peroid == null ? null : peroid.trim();
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