package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime21302140;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime21302140Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime21302140 record);

    Avgtime21302140 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime21302140> selectAll();

    int updateByPrimaryKey(Avgtime21302140 record);
}