package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime13001330;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime13001330Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime13001330 record);

    Avgtime13001330 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime13001330> selectAll();

    int updateByPrimaryKey(Avgtime13001330 record);
}