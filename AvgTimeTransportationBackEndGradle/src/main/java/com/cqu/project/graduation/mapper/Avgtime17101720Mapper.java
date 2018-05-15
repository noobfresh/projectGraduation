package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime17101720;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime17101720Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime17101720 record);

    Avgtime17101720 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime17101720> selectAll();

    int updateByPrimaryKey(Avgtime17101720 record);
}