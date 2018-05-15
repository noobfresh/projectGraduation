package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime21302200;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime21302200Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime21302200 record);

    Avgtime21302200 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime21302200> selectAll();

    int updateByPrimaryKey(Avgtime21302200 record);
}