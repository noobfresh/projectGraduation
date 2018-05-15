package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime20170901;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime20170901Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime20170901 record);

    Avgtime20170901 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime20170901> selectAll();

    int updateByPrimaryKey(Avgtime20170901 record);
}