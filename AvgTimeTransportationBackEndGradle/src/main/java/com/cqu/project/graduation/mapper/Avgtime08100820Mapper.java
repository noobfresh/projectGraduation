package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime08100820;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime08100820Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime08100820 record);

    Avgtime08100820 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime08100820> selectAll();

    int updateByPrimaryKey(Avgtime08100820 record);
}