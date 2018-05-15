package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime09400950;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime09400950Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime09400950 record);

    Avgtime09400950 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime09400950> selectAll();

    int updateByPrimaryKey(Avgtime09400950 record);
}