package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime17001730;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime17001730Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime17001730 record);

    Avgtime17001730 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime17001730> selectAll();

    int updateByPrimaryKey(Avgtime17001730 record);
}