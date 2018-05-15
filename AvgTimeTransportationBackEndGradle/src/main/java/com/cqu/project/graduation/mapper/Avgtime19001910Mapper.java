package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime19001910;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime19001910Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime19001910 record);

    Avgtime19001910 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime19001910> selectAll();

    int updateByPrimaryKey(Avgtime19001910 record);
}