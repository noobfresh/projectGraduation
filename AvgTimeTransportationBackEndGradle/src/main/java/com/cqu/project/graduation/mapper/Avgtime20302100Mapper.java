package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime20302100;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime20302100Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime20302100 record);

    Avgtime20302100 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime20302100> selectAll();

    int updateByPrimaryKey(Avgtime20302100 record);
}