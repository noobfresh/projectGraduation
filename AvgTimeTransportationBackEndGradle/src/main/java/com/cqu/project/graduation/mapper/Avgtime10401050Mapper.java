package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime10401050;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime10401050Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime10401050 record);

    Avgtime10401050 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime10401050> selectAll();

    int updateByPrimaryKey(Avgtime10401050 record);
}