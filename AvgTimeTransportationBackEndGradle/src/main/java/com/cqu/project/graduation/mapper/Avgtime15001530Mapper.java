package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime15001530;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime15001530Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime15001530 record);

    Avgtime15001530 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime15001530> selectAll();

    int updateByPrimaryKey(Avgtime15001530 record);
}