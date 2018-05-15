package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime19301940;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime19301940Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime19301940 record);

    Avgtime19301940 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime19301940> selectAll();

    int updateByPrimaryKey(Avgtime19301940 record);
}