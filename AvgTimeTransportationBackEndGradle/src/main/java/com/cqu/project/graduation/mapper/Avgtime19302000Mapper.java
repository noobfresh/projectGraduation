package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime19302000;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime19302000Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime19302000 record);

    Avgtime19302000 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime19302000> selectAll();

    int updateByPrimaryKey(Avgtime19302000 record);
}