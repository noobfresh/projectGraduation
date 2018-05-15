package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime13301340;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime13301340Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime13301340 record);

    Avgtime13301340 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime13301340> selectAll();

    int updateByPrimaryKey(Avgtime13301340 record);
}