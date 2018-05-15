package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime10101020;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime10101020Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime10101020 record);

    Avgtime10101020 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime10101020> selectAll();

    int updateByPrimaryKey(Avgtime10101020 record);
}