package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime18001810;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime18001810Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime18001810 record);

    Avgtime18001810 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime18001810> selectAll();

    int updateByPrimaryKey(Avgtime18001810 record);
}