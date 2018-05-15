package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime20102020;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime20102020Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime20102020 record);

    Avgtime20102020 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime20102020> selectAll();

    int updateByPrimaryKey(Avgtime20102020 record);
}