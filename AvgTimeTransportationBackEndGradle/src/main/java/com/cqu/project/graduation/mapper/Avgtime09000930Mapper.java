package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime09000930;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime09000930Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime09000930 record);

    Avgtime09000930 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime09000930> selectAll();

    int updateByPrimaryKey(Avgtime09000930 record);
}