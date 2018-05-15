package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime16201630;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime16201630Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime16201630 record);

    Avgtime16201630 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime16201630> selectAll();

    int updateByPrimaryKey(Avgtime16201630 record);
}