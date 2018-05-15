package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime14201430;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime14201430Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime14201430 record);

    Avgtime14201430 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime14201430> selectAll();

    int updateByPrimaryKey(Avgtime14201430 record);
}