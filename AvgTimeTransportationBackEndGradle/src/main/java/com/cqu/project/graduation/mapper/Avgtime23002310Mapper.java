package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime23002310;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime23002310Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime23002310 record);

    Avgtime23002310 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime23002310> selectAll();

    int updateByPrimaryKey(Avgtime23002310 record);
}