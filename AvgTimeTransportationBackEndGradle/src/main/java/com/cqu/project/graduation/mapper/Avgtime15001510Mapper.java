package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime15001510;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime15001510Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime15001510 record);

    Avgtime15001510 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime15001510> selectAll();

    int updateByPrimaryKey(Avgtime15001510 record);
}