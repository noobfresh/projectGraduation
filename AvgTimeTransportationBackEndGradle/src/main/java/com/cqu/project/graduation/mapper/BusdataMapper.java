package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Busdata;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusdataMapper {
    int insert(Busdata record);

    List<Busdata> selectAll();

    Busdata selectByCondition(@Param("lineNo") String lineNo,
                          @Param("startStation") String startStation,
                          @Param("endStation") String endStation,
                          @Param("direction") String direction,
                          @Param("period") String period);

    Busdata selectByTable(@Param("lineNo") String lineNo,
                              @Param("startStation") String startStation,
                              @Param("endStation") String endStation,
                              @Param("direction") String direction,
                              @Param("period") String period,
                              @Param("tableName") String tableName);

    List<Busdata> selectWithoutPeriod(@Param("lineNo") String lineNo,
                                      @Param("startStation") String startStation,
                                      @Param("endStation") String endStation,
                                      @Param("direction") String direction,
                                      @Param("tableName") String tableName);
}