package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime15201530;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime15201530Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime15201530 record);

    Avgtime15201530 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime15201530> selectAll();

    int updateByPrimaryKey(Avgtime15201530 record);
}