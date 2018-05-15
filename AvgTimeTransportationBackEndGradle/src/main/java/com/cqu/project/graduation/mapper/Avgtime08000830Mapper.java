package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime08000830;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime08000830Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime08000830 record);

    Avgtime08000830 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime08000830> selectAll();

    int updateByPrimaryKey(Avgtime08000830 record);
}