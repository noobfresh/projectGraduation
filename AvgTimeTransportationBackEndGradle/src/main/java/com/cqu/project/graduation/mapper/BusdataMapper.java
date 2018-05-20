package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Busdata;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusdataMapper {
    int insert(Busdata record);

    List<Busdata> selectAll();

    Busdata selectByLineStationPeriod(@Param("lineNo") String lineNo,
                          @Param("startStation") String startStation,
                          @Param("endStation") String endStation);
}