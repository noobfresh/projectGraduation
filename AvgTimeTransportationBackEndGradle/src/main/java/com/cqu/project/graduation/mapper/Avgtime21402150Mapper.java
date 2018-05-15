package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime21402150;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime21402150Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime21402150 record);

    Avgtime21402150 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime21402150> selectAll();

    int updateByPrimaryKey(Avgtime21402150 record);
}