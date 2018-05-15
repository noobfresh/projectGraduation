package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime23302340;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime23302340Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime23302340 record);

    Avgtime23302340 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime23302340> selectAll();

    int updateByPrimaryKey(Avgtime23302340 record);
}