package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime10001010;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime10001010Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime10001010 record);

    Avgtime10001010 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime10001010> selectAll();

    int updateByPrimaryKey(Avgtime10001010 record);
}