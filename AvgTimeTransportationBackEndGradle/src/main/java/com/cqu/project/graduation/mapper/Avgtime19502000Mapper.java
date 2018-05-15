package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime19502000;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime19502000Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime19502000 record);

    Avgtime19502000 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime19502000> selectAll();

    int updateByPrimaryKey(Avgtime19502000 record);
}