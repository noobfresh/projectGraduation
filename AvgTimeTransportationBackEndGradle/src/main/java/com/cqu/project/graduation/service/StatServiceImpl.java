package com.cqu.project.graduation.service;

import com.cqu.project.graduation.entity.Statistic;
import com.cqu.project.graduation.mapper.StatisticMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StatServiceImpl implements IStatService {

    StatisticMapper statisticMapper;

    @Autowired
    public StatServiceImpl(StatisticMapper statisticMapper) {
        this.statisticMapper = statisticMapper;
    }

    @Override
    public Statistic selectByTableName(String tableName) {
        return statisticMapper.selectByPrimaryKey(tableName);
    }
}
