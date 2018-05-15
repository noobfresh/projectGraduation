package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime17401750;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime17401750Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime17401750 record);

    Avgtime17401750 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime17401750> selectAll();

    int updateByPrimaryKey(Avgtime17401750 record);
}