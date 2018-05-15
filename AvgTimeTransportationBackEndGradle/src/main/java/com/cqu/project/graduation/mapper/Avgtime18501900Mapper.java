package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime18501900;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime18501900Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime18501900 record);

    Avgtime18501900 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime18501900> selectAll();

    int updateByPrimaryKey(Avgtime18501900 record);
}