package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime16001610;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime16001610Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime16001610 record);

    Avgtime16001610 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime16001610> selectAll();

    int updateByPrimaryKey(Avgtime16001610 record);
}