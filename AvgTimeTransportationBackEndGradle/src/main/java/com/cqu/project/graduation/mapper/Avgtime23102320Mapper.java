package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime23102320;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime23102320Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime23102320 record);

    Avgtime23102320 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime23102320> selectAll();

    int updateByPrimaryKey(Avgtime23102320 record);
}