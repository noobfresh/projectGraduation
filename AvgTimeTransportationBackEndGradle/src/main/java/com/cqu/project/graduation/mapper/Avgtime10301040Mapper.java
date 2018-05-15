package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime10301040;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime10301040Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime10301040 record);

    Avgtime10301040 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime10301040> selectAll();

    int updateByPrimaryKey(Avgtime10301040 record);
}