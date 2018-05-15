package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime18001830;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime18001830Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime18001830 record);

    Avgtime18001830 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime18001830> selectAll();

    int updateByPrimaryKey(Avgtime18001830 record);
}