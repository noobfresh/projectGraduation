package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime21002130;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime21002130Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime21002130 record);

    Avgtime21002130 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime21002130> selectAll();

    int updateByPrimaryKey(Avgtime21002130 record);
}