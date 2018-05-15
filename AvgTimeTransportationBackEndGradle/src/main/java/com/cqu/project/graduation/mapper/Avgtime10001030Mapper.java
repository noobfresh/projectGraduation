package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime10001030;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime10001030Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime10001030 record);

    Avgtime10001030 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime10001030> selectAll();

    int updateByPrimaryKey(Avgtime10001030 record);
}