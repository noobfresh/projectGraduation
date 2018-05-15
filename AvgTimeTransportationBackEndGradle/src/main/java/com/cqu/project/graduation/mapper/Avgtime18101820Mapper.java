package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime18101820;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime18101820Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime18101820 record);

    Avgtime18101820 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime18101820> selectAll();

    int updateByPrimaryKey(Avgtime18101820 record);
}