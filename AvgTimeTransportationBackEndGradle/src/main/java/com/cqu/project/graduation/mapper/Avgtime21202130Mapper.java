package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime21202130;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime21202130Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime21202130 record);

    Avgtime21202130 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime21202130> selectAll();

    int updateByPrimaryKey(Avgtime21202130 record);
}