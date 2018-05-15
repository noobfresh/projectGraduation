package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime07500800;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime07500800Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime07500800 record);

    Avgtime07500800 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime07500800> selectAll();

    int updateByPrimaryKey(Avgtime07500800 record);
}