package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime09200930;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime09200930Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime09200930 record);

    Avgtime09200930 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime09200930> selectAll();

    int updateByPrimaryKey(Avgtime09200930 record);
}