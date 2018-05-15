package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime07100720;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime07100720Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime07100720 record);

    Avgtime07100720 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime07100720> selectAll();

    int updateByPrimaryKey(Avgtime07100720 record);
}