package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime20302040;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime20302040Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime20302040 record);

    Avgtime20302040 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime20302040> selectAll();

    int updateByPrimaryKey(Avgtime20302040 record);
}