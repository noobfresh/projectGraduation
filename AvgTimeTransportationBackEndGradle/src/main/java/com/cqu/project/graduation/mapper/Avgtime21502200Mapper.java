package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime21502200;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime21502200Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime21502200 record);

    Avgtime21502200 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime21502200> selectAll();

    int updateByPrimaryKey(Avgtime21502200 record);
}