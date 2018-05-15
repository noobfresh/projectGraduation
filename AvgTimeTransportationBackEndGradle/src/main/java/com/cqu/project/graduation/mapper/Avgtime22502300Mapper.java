package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime22502300;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime22502300Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime22502300 record);

    Avgtime22502300 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime22502300> selectAll();

    int updateByPrimaryKey(Avgtime22502300 record);
}