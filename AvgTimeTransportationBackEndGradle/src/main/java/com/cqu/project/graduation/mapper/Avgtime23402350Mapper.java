package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime23402350;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime23402350Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime23402350 record);

    Avgtime23402350 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime23402350> selectAll();

    int updateByPrimaryKey(Avgtime23402350 record);
}