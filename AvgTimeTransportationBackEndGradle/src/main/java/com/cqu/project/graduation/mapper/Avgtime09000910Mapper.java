package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime09000910;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime09000910Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime09000910 record);

    Avgtime09000910 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime09000910> selectAll();

    int updateByPrimaryKey(Avgtime09000910 record);
}