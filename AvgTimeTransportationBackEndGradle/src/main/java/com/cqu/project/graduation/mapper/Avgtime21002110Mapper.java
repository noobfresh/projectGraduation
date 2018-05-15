package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime21002110;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime21002110Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime21002110 record);

    Avgtime21002110 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime21002110> selectAll();

    int updateByPrimaryKey(Avgtime21002110 record);
}