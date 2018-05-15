package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime12201230;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime12201230Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime12201230 record);

    Avgtime12201230 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime12201230> selectAll();

    int updateByPrimaryKey(Avgtime12201230 record);
}