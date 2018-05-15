package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime20170915;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime20170915Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime20170915 record);

    Avgtime20170915 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime20170915> selectAll();

    int updateByPrimaryKey(Avgtime20170915 record);
}