package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime23500000;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime23500000Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime23500000 record);

    Avgtime23500000 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime23500000> selectAll();

    int updateByPrimaryKey(Avgtime23500000 record);
}