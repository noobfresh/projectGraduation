package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime13501400;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime13501400Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime13501400 record);

    Avgtime13501400 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime13501400> selectAll();

    int updateByPrimaryKey(Avgtime13501400 record);
}