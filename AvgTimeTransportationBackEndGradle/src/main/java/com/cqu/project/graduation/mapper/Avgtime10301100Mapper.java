package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime10301100;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime10301100Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime10301100 record);

    Avgtime10301100 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime10301100> selectAll();

    int updateByPrimaryKey(Avgtime10301100 record);
}