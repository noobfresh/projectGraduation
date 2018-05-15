package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime14001430;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime14001430Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime14001430 record);

    Avgtime14001430 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime14001430> selectAll();

    int updateByPrimaryKey(Avgtime14001430 record);
}