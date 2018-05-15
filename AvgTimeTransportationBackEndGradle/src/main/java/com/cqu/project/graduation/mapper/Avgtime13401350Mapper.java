package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime13401350;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime13401350Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime13401350 record);

    Avgtime13401350 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime13401350> selectAll();

    int updateByPrimaryKey(Avgtime13401350 record);
}