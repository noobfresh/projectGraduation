package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime06300700;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime06300700Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime06300700 record);

    Avgtime06300700 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime06300700> selectAll();

    int updateByPrimaryKey(Avgtime06300700 record);
}