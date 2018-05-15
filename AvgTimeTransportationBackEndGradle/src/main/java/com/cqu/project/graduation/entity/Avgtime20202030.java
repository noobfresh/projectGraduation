package com.cqu.project.graduation.entity;

public class Avgtime20202030 {
    private String startStation;

    private String endStation;

    private String direction;

    private Integer duration;

    private Float distance;

    private String time;

    private String isbus;

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

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction == null ? null : direction.trim();
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    public String getIsbus() {
        return isbus;
    }

    public void setIsbus(String isbus) {
        this.isbus = isbus == null ? null : isbus.trim();
    }
}