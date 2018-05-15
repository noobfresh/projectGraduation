package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime19401950;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime19401950Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime19401950 record);

    Avgtime19401950 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime19401950> selectAll();

    int updateByPrimaryKey(Avgtime19401950 record);
}