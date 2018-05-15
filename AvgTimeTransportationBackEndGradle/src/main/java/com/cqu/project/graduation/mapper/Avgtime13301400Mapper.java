package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime13301400;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime13301400Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime13301400 record);

    Avgtime13301400 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime13301400> selectAll();

    int updateByPrimaryKey(Avgtime13301400 record);
}