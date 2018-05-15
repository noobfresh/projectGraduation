package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime06500700;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime06500700Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime06500700 record);

    Avgtime06500700 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime06500700> selectAll();

    int updateByPrimaryKey(Avgtime06500700 record);
}