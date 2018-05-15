package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime16301700;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime16301700Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime16301700 record);

    Avgtime16301700 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime16301700> selectAll();

    int updateByPrimaryKey(Avgtime16301700 record);
}