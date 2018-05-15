package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime16001630;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime16001630Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime16001630 record);

    Avgtime16001630 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime16001630> selectAll();

    int updateByPrimaryKey(Avgtime16001630 record);
}