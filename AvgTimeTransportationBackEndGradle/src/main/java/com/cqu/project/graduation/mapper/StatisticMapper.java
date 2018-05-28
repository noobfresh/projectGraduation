package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Statistic;
import java.util.List;

public interface StatisticMapper {
    int deleteByPrimaryKey(String tablename);

    int insert(Statistic record);

    Statistic selectByPrimaryKey(String tablename);

    List<Statistic> selectAll();

    int updateByPrimaryKey(Statistic record);
}