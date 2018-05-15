package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime11301140;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime11301140Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime11301140 record);

    Avgtime11301140 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime11301140> selectAll();

    int updateByPrimaryKey(Avgtime11301140 record);
}