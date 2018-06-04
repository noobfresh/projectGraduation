package com.cqu.project.graduation.service;

import com.cqu.project.graduation.entity.PredictAvgtime;
import com.cqu.project.graduation.entity.PredictAvgtimeWeek;
import com.cqu.project.graduation.entity.PredictBusDay;
import com.cqu.project.graduation.entity.PredictBusWeek;

import java.util.List;

public interface IPredictService {

    PredictAvgtime selectByPrimary(String startStation, String endStation, String tableName);

    List<PredictAvgtimeWeek> selectByTimeRange(String startDate, String endDate,
                                               String startStation, String endStation);

    PredictBusDay selectByPrimaryBus(String lineNo, String startStation,
                                     String endStation, String date, String period);

    List<PredictBusWeek> selecByTimeRange(String startDate, String endDate,
                                          String startStation, String endStation, String lineNo);
}
