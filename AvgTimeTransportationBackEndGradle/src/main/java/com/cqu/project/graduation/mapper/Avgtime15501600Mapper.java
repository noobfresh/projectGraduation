package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime15501600;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime15501600Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime15501600 record);

    Avgtime15501600 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime15501600> selectAll();

    int updateByPrimaryKey(Avgtime15501600 record);
}