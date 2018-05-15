package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime20502100;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime20502100Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime20502100 record);

    Avgtime20502100 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime20502100> selectAll();

    int updateByPrimaryKey(Avgtime20502100 record);
}