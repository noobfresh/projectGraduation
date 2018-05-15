package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime07200730;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime07200730Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime07200730 record);

    Avgtime07200730 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime07200730> selectAll();

    int updateByPrimaryKey(Avgtime07200730 record);
}