package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime14501500;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime14501500Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime14501500 record);

    Avgtime14501500 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime14501500> selectAll();

    int updateByPrimaryKey(Avgtime14501500 record);
}