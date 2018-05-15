package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime15301600;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime15301600Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime15301600 record);

    Avgtime15301600 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime15301600> selectAll();

    int updateByPrimaryKey(Avgtime15301600 record);
}