package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime06000630;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime06000630Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime06000630 record);

    Avgtime06000630 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime06000630> selectAll();

    int updateByPrimaryKey(Avgtime06000630 record);
}