package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime21102120;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime21102120Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime21102120 record);

    Avgtime21102120 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime21102120> selectAll();

    int updateByPrimaryKey(Avgtime21102120 record);
}