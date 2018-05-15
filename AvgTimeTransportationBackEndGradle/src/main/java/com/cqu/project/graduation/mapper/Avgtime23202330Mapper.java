package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime23202330;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime23202330Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime23202330 record);

    Avgtime23202330 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime23202330> selectAll();

    int updateByPrimaryKey(Avgtime23202330 record);
}