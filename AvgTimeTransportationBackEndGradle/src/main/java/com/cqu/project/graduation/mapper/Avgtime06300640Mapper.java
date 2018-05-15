package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime06300640;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime06300640Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime06300640 record);

    Avgtime06300640 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime06300640> selectAll();

    int updateByPrimaryKey(Avgtime06300640 record);
}