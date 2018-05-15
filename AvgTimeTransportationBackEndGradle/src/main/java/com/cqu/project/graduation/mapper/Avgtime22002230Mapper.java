package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime22002230;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime22002230Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime22002230 record);

    Avgtime22002230 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime22002230> selectAll();

    int updateByPrimaryKey(Avgtime22002230 record);
}