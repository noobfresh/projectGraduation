package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime17001710;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime17001710Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime17001710 record);

    Avgtime17001710 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime17001710> selectAll();

    int updateByPrimaryKey(Avgtime17001710 record);
}