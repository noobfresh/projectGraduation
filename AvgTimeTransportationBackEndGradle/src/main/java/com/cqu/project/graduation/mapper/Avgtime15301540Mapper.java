package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime15301540;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime15301540Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime15301540 record);

    Avgtime15301540 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime15301540> selectAll();

    int updateByPrimaryKey(Avgtime15301540 record);
}