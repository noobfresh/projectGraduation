package com.cqu.project.graduation.service;


import com.cqu.project.graduation.entity.Avgtime;
import com.cqu.project.graduation.entity.Busdata;
import com.cqu.project.graduation.mapper.AvgtimeMapper;

public interface IAvgTimeService {

    Avgtime getAvgTimeByTest(String startStation, String endStation, String tableName);
    Busdata getBusdataByCondition(String lineNo, String startStation,
                                  String endStation, String direction,
                                  String period);
}
