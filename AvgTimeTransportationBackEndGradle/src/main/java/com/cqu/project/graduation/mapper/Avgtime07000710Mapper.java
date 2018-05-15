package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime07000710;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime07000710Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime07000710 record);

    Avgtime07000710 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime07000710> selectAll();

    int updateByPrimaryKey(Avgtime07000710 record);
}