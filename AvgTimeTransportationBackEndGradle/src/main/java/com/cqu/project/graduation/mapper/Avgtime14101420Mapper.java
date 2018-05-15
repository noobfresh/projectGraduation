package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime14101420;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime14101420Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime14101420 record);

    Avgtime14101420 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime14101420> selectAll();

    int updateByPrimaryKey(Avgtime14101420 record);
}