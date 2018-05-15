package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime17301800;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime17301800Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime17301800 record);

    Avgtime17301800 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime17301800> selectAll();

    int updateByPrimaryKey(Avgtime17301800 record);
}