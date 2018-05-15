package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime22102220;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime22102220Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime22102220 record);

    Avgtime22102220 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime22102220> selectAll();

    int updateByPrimaryKey(Avgtime22102220 record);
}