package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime08300900;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime08300900Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime08300900 record);

    Avgtime08300900 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime08300900> selectAll();

    int updateByPrimaryKey(Avgtime08300900 record);
}