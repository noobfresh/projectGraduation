package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime11001110;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime11001110Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime11001110 record);

    Avgtime11001110 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime11001110> selectAll();

    int updateByPrimaryKey(Avgtime11001110 record);
}