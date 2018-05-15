package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime12301300;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime12301300Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime12301300 record);

    Avgtime12301300 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime12301300> selectAll();

    int updateByPrimaryKey(Avgtime12301300 record);
}