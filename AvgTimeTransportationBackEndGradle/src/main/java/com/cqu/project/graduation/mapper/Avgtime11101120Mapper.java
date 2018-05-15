package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime11101120;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime11101120Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime11101120 record);

    Avgtime11101120 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime11101120> selectAll();

    int updateByPrimaryKey(Avgtime11101120 record);
}