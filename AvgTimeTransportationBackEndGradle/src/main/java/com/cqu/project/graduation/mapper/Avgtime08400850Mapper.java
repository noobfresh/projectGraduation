package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime08400850;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime08400850Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime08400850 record);

    Avgtime08400850 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime08400850> selectAll();

    int updateByPrimaryKey(Avgtime08400850 record);
}