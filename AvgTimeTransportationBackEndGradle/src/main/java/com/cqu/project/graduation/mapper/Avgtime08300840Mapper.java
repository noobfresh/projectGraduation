package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime08300840;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime08300840Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime08300840 record);

    Avgtime08300840 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime08300840> selectAll();

    int updateByPrimaryKey(Avgtime08300840 record);
}