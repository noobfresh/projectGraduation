package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime22302300;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime22302300Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime22302300 record);

    Avgtime22302300 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime22302300> selectAll();

    int updateByPrimaryKey(Avgtime22302300 record);
}