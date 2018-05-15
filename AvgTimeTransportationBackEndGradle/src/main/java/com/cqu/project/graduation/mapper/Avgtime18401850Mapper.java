package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime18401850;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime18401850Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime18401850 record);

    Avgtime18401850 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime18401850> selectAll();

    int updateByPrimaryKey(Avgtime18401850 record);
}