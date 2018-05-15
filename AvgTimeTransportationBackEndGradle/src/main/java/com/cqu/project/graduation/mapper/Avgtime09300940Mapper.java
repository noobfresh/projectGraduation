package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime09300940;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime09300940Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime09300940 record);

    Avgtime09300940 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime09300940> selectAll();

    int updateByPrimaryKey(Avgtime09300940 record);
}