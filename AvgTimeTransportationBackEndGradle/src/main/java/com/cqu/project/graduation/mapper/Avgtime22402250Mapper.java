package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime22402250;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime22402250Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime22402250 record);

    Avgtime22402250 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime22402250> selectAll();

    int updateByPrimaryKey(Avgtime22402250 record);
}