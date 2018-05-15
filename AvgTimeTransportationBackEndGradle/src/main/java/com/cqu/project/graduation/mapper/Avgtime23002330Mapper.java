package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime23002330;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime23002330Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime23002330 record);

    Avgtime23002330 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime23002330> selectAll();

    int updateByPrimaryKey(Avgtime23002330 record);
}