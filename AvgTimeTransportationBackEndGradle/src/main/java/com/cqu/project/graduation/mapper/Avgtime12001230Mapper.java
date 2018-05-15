package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime12001230;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime12001230Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime12001230 record);

    Avgtime12001230 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime12001230> selectAll();

    int updateByPrimaryKey(Avgtime12001230 record);
}