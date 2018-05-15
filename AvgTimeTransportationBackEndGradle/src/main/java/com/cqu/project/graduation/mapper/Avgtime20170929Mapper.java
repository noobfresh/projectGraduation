package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime20170929;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime20170929Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime20170929 record);

    Avgtime20170929 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime20170929> selectAll();

    int updateByPrimaryKey(Avgtime20170929 record);
}