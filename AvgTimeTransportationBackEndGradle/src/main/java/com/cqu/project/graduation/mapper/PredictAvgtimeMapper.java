package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.PredictAvgtime;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PredictAvgtimeMapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(PredictAvgtime record);

    PredictAvgtime selectByPrimaryKey(@Param("startStation") String startStation,
                                      @Param("endStation") String endStation,
                                      @Param("tableName") String tableName);

    List<PredictAvgtime> selectAll();

    int updateByPrimaryKey(PredictAvgtime record);
}