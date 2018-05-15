package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime14301500;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime14301500Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime14301500 record);

    Avgtime14301500 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime14301500> selectAll();

    int updateByPrimaryKey(Avgtime14301500 record);
}