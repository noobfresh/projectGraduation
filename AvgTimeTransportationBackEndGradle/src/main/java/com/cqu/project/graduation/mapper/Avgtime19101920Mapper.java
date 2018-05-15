package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime19101920;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime19101920Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime19101920 record);

    Avgtime19101920 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime19101920> selectAll();

    int updateByPrimaryKey(Avgtime19101920 record);
}