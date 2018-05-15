package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime11001130;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime11001130Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime11001130 record);

    Avgtime11001130 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime11001130> selectAll();

    int updateByPrimaryKey(Avgtime11001130 record);
}