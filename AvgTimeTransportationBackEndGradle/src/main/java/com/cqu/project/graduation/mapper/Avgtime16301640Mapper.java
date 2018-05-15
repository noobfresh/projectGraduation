package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime16301640;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime16301640Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime16301640 record);

    Avgtime16301640 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime16301640> selectAll();

    int updateByPrimaryKey(Avgtime16301640 record);
}