package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime10201030;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime10201030Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime10201030 record);

    Avgtime10201030 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime10201030> selectAll();

    int updateByPrimaryKey(Avgtime10201030 record);
}