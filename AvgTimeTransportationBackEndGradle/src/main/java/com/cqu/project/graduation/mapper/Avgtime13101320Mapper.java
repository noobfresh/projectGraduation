package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime13101320;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime13101320Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime13101320 record);

    Avgtime13101320 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime13101320> selectAll();

    int updateByPrimaryKey(Avgtime13101320 record);
}