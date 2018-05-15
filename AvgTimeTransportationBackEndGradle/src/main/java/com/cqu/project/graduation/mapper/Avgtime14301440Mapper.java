package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime14301440;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime14301440Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime14301440 record);

    Avgtime14301440 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime14301440> selectAll();

    int updateByPrimaryKey(Avgtime14301440 record);
}