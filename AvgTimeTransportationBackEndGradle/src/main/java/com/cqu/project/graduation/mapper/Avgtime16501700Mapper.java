package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime16501700;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime16501700Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime16501700 record);

    Avgtime16501700 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime16501700> selectAll();

    int updateByPrimaryKey(Avgtime16501700 record);
}