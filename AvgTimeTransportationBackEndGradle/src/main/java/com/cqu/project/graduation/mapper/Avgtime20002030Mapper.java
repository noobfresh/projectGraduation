package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime20002030;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime20002030Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime20002030 record);

    Avgtime20002030 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime20002030> selectAll();

    int updateByPrimaryKey(Avgtime20002030 record);
}