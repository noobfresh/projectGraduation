package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime09501000;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime09501000Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime09501000 record);

    Avgtime09501000 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime09501000> selectAll();

    int updateByPrimaryKey(Avgtime09501000 record);
}