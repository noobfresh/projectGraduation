package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime06400650;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime06400650Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime06400650 record);

    Avgtime06400650 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime06400650> selectAll();

    int updateByPrimaryKey(Avgtime06400650 record);
}