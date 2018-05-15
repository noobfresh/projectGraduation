package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime14001410;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime14001410Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime14001410 record);

    Avgtime14001410 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime14001410> selectAll();

    int updateByPrimaryKey(Avgtime14001410 record);
}