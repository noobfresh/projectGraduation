package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime06200630;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime06200630Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime06200630 record);

    Avgtime06200630 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime06200630> selectAll();

    int updateByPrimaryKey(Avgtime06200630 record);
}