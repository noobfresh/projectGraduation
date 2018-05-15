package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime08500900;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime08500900Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime08500900 record);

    Avgtime08500900 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime08500900> selectAll();

    int updateByPrimaryKey(Avgtime08500900 record);
}