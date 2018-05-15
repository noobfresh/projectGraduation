package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime06000610;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime06000610Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime06000610 record);

    Avgtime06000610 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime06000610> selectAll();

    int updateByPrimaryKey(Avgtime06000610 record);
}