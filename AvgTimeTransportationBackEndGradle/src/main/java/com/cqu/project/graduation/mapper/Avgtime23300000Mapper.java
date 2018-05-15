package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime23300000;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime23300000Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime23300000 record);

    Avgtime23300000 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime23300000> selectAll();

    int updateByPrimaryKey(Avgtime23300000 record);
}