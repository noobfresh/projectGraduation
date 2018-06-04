package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.PredictAvgtimeWeek;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PredictAvgtimeWeekMapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation, @Param("date") String date);

    int insert(PredictAvgtimeWeek record);

    PredictAvgtimeWeek selectByPrimaryKey(@Param("startStation") String startStation,
                                          @Param("endStation") String endStation,
                                          @Param("date") String date);

    List<PredictAvgtimeWeek> selectAll();

    int updateByPrimaryKey(PredictAvgtimeWeek record);
}