package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime12301240;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime12301240Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime12301240 record);

    Avgtime12301240 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime12301240> selectAll();

    int updateByPrimaryKey(Avgtime12301240 record);
}