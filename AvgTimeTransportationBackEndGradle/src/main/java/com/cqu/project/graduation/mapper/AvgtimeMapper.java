package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AvgtimeMapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation,
                           @Param("endStation") String endStation);

    int insert(Avgtime record);

    Avgtime selectByPrimaryKey(@Param("startStation") String startStation,
                               @Param("endStation") String endStation,
                               @Param("tableName") String tableName);

    List<Avgtime> selectAll();

    int updateByPrimaryKey(Avgtime record);
}