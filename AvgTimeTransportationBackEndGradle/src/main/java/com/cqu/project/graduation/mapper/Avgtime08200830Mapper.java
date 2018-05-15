package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime08200830;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime08200830Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime08200830 record);

    Avgtime08200830 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime08200830> selectAll();

    int updateByPrimaryKey(Avgtime08200830 record);
}