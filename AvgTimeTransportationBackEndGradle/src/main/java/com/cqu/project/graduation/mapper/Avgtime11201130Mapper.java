package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime11201130;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime11201130Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime11201130 record);

    Avgtime11201130 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime11201130> selectAll();

    int updateByPrimaryKey(Avgtime11201130 record);
}