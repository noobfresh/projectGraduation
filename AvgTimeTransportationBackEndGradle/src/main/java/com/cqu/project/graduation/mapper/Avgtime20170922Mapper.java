package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime20170922;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime20170922Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime20170922 record);

    Avgtime20170922 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime20170922> selectAll();

    int updateByPrimaryKey(Avgtime20170922 record);
}