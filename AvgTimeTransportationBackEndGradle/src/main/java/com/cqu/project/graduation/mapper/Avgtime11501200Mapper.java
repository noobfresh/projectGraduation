package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime11501200;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime11501200Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime11501200 record);

    Avgtime11501200 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime11501200> selectAll();

    int updateByPrimaryKey(Avgtime11501200 record);
}