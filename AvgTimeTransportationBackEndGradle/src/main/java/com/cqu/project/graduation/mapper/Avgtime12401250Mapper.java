package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime12401250;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime12401250Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime12401250 record);

    Avgtime12401250 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime12401250> selectAll();

    int updateByPrimaryKey(Avgtime12401250 record);
}