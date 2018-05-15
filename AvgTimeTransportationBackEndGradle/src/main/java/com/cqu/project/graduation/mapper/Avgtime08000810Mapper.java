package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime08000810;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime08000810Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime08000810 record);

    Avgtime08000810 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime08000810> selectAll();

    int updateByPrimaryKey(Avgtime08000810 record);
}