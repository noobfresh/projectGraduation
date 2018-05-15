package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime18201830;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime18201830Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime18201830 record);

    Avgtime18201830 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime18201830> selectAll();

    int updateByPrimaryKey(Avgtime18201830 record);
}