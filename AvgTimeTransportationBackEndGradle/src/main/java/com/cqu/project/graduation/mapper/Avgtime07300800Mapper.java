package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime07300800;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime07300800Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime07300800 record);

    Avgtime07300800 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime07300800> selectAll();

    int updateByPrimaryKey(Avgtime07300800 record);
}