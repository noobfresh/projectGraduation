package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime10501100;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime10501100Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime10501100 record);

    Avgtime10501100 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime10501100> selectAll();

    int updateByPrimaryKey(Avgtime10501100 record);
}