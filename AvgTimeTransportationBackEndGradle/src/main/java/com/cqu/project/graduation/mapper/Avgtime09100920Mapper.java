package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime09100920;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime09100920Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime09100920 record);

    Avgtime09100920 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime09100920> selectAll();

    int updateByPrimaryKey(Avgtime09100920 record);
}