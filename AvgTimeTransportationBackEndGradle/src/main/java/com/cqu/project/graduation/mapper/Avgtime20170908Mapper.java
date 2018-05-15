package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime20170908;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime20170908Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime20170908 record);

    Avgtime20170908 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime20170908> selectAll();

    int updateByPrimaryKey(Avgtime20170908 record);
}