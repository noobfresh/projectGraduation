package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime16401650;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime16401650Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime16401650 record);

    Avgtime16401650 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime16401650> selectAll();

    int updateByPrimaryKey(Avgtime16401650 record);
}