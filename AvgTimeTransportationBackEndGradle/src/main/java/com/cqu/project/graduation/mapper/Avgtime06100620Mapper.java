package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime06100620;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime06100620Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime06100620 record);

    Avgtime06100620 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime06100620> selectAll();

    int updateByPrimaryKey(Avgtime06100620 record);
}