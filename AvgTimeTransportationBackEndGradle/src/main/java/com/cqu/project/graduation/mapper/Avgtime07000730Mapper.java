package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime07000730;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime07000730Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime07000730 record);

    Avgtime07000730 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime07000730> selectAll();

    int updateByPrimaryKey(Avgtime07000730 record);
}