package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime11401150;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime11401150Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime11401150 record);

    Avgtime11401150 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime11401150> selectAll();

    int updateByPrimaryKey(Avgtime11401150 record);
}