package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime20202030;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime20202030Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime20202030 record);

    Avgtime20202030 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime20202030> selectAll();

    int updateByPrimaryKey(Avgtime20202030 record);
}