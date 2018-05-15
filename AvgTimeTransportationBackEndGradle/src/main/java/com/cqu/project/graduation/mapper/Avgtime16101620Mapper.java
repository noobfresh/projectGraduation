package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime16101620;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime16101620Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime16101620 record);

    Avgtime16101620 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime16101620> selectAll();

    int updateByPrimaryKey(Avgtime16101620 record);
}