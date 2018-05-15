package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime19201930;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime19201930Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime19201930 record);

    Avgtime19201930 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime19201930> selectAll();

    int updateByPrimaryKey(Avgtime19201930 record);
}