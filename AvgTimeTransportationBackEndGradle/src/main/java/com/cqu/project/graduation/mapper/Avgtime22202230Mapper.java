package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime22202230;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime22202230Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime22202230 record);

    Avgtime22202230 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime22202230> selectAll();

    int updateByPrimaryKey(Avgtime22202230 record);
}