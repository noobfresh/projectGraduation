package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime18301900;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime18301900Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime18301900 record);

    Avgtime18301900 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime18301900> selectAll();

    int updateByPrimaryKey(Avgtime18301900 record);
}