package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime18301840;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime18301840Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime18301840 record);

    Avgtime18301840 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime18301840> selectAll();

    int updateByPrimaryKey(Avgtime18301840 record);
}