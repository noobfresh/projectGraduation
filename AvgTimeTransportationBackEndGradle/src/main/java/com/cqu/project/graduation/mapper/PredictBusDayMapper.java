package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.PredictBusDay;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PredictBusDayMapper {
    int deleteByPrimaryKey(@Param("lineNo") String lineNo, @Param("startStation") String startStation, @Param("endStation") String endStation, @Param("date") String date, @Param("peroid") String peroid);

    int insert(PredictBusDay record);

    PredictBusDay selectByPrimaryKey(@Param("lineNo") String lineNo,
                                     @Param("startStation") String startStation,
                                     @Param("endStation") String endStation,
                                     @Param("date") String date, @Param("peroid") String peroid);

    List<PredictBusDay> selectAll();

    int updateByPrimaryKey(PredictBusDay record);
}