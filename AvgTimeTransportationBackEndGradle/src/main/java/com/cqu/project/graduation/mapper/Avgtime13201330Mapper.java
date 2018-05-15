package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime13201330;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime13201330Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime13201330 record);

    Avgtime13201330 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime13201330> selectAll();

    int updateByPrimaryKey(Avgtime13201330 record);
}