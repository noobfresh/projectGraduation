package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime12501300;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime12501300Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime12501300 record);

    Avgtime12501300 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime12501300> selectAll();

    int updateByPrimaryKey(Avgtime12501300 record);
}