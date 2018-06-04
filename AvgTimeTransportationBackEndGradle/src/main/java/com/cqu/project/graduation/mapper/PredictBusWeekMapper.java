package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.PredictBusWeek;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PredictBusWeekMapper {
    int deleteByPrimaryKey(@Param("lineNo") String lineNo, @Param("startStation") String startStation, @Param("endStation") String endStation, @Param("date") String date);

    int insert(PredictBusWeek record);

    PredictBusWeek selectByPrimaryKey(@Param("lineNo") String lineNo,
                                      @Param("startStation") String startStation,
                                      @Param("endStation") String endStation,
                                      @Param("date") String date);

    List<PredictBusWeek> selectAll();

    int updateByPrimaryKey(PredictBusWeek record);
}