package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime17201730;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime17201730Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime17201730 record);

    Avgtime17201730 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime17201730> selectAll();

    int updateByPrimaryKey(Avgtime17201730 record);
}