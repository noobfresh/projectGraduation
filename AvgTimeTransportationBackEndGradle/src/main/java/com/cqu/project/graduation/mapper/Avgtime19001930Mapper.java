package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime19001930;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime19001930Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime19001930 record);

    Avgtime19001930 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime19001930> selectAll();

    int updateByPrimaryKey(Avgtime19001930 record);
}