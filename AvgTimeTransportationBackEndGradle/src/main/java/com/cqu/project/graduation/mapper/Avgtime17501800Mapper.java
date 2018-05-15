package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime17501800;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime17501800Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime17501800 record);

    Avgtime17501800 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime17501800> selectAll();

    int updateByPrimaryKey(Avgtime17501800 record);
}