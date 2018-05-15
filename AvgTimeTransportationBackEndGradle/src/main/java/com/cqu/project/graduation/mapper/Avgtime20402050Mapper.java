package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime20402050;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime20402050Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime20402050 record);

    Avgtime20402050 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime20402050> selectAll();

    int updateByPrimaryKey(Avgtime20402050 record);
}