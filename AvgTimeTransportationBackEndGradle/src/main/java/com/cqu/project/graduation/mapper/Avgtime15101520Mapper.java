package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime15101520;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime15101520Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime15101520 record);

    Avgtime15101520 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime15101520> selectAll();

    int updateByPrimaryKey(Avgtime15101520 record);
}