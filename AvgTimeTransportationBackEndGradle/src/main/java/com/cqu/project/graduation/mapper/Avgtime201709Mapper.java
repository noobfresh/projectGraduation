package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime201709;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime201709Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime201709 record);

    Avgtime201709 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime201709> selectAll();

    int updateByPrimaryKey(Avgtime201709 record);
}