package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime20002010;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime20002010Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime20002010 record);

    Avgtime20002010 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime20002010> selectAll();

    int updateByPrimaryKey(Avgtime20002010 record);
}