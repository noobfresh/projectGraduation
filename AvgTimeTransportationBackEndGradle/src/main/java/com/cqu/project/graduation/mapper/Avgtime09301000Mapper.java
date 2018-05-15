package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime09301000;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime09301000Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime09301000 record);

    Avgtime09301000 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime09301000> selectAll();

    int updateByPrimaryKey(Avgtime09301000 record);
}