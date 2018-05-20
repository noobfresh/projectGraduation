package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Od;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OdMapper {
    int deleteByPrimaryKey(@Param("startId") Integer startId, @Param("endId") Integer endId);

    int insert(Od record);

    Od selectByPrimaryKey(@Param("startId") Integer startId, @Param("endId") Integer endId);

    List<Od> selectAll();

    int updateByPrimaryKey(Od record);
}