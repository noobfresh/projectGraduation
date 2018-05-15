package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime14401450;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime14401450Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime14401450 record);

    Avgtime14401450 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime14401450> selectAll();

    int updateByPrimaryKey(Avgtime14401450 record);
}