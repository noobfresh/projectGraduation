package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime22302240;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime22302240Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime22302240 record);

    Avgtime22302240 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime22302240> selectAll();

    int updateByPrimaryKey(Avgtime22302240 record);
}