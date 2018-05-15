package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime12001210;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime12001210Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime12001210 record);

    Avgtime12001210 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime12001210> selectAll();

    int updateByPrimaryKey(Avgtime12001210 record);
}