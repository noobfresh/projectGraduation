package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime22002210;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime22002210Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime22002210 record);

    Avgtime22002210 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime22002210> selectAll();

    int updateByPrimaryKey(Avgtime22002210 record);
}