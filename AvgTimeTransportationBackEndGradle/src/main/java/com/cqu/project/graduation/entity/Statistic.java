package com.cqu.project.graduation.entity;

public class Statistic {
    private String tablename;

    private Integer missingnum;

    private Integer havingnum;

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename == null ? null : tablename.trim();
    }

    public Integer getMissingnum() {
        return missingnum;
    }

    public void setMissingnum(Integer missingnum) {
        this.missingnum = missingnum;
    }

    public Integer getHavingnum() {
        return havingnum;
    }

    public void setHavingnum(Integer havingnum) {
        this.havingnum = havingnum;
    }
}