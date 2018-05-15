package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime07300740;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime07300740Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime07300740 record);

    Avgtime07300740 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime07300740> selectAll();

    int updateByPrimaryKey(Avgtime07300740 record);
}