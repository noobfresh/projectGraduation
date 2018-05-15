package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime15401550;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime15401550Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime15401550 record);

    Avgtime15401550 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime15401550> selectAll();

    int updateByPrimaryKey(Avgtime15401550 record);
}