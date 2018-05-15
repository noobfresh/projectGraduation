package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime12101220;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime12101220Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime12101220 record);

    Avgtime12101220 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime12101220> selectAll();

    int updateByPrimaryKey(Avgtime12101220 record);
}