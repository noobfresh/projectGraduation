package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime17301740;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime17301740Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime17301740 record);

    Avgtime17301740 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime17301740> selectAll();

    int updateByPrimaryKey(Avgtime17301740 record);
}