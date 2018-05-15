package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime13001310;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime13001310Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime13001310 record);

    Avgtime13001310 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime13001310> selectAll();

    int updateByPrimaryKey(Avgtime13001310 record);
}