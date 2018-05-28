package com.cqu.project.graduation.service;


import com.cqu.project.graduation.entity.Avgtime;
import com.cqu.project.graduation.entity.Busdata;
import com.cqu.project.graduation.mapper.AvgtimeMapper;
import com.cqu.project.graduation.mapper.BusdataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AvgTimeServiceImpl implements IAvgTimeService{

    private AvgtimeMapper mapper;
    private BusdataMapper busdataMapper;

    @Autowired
    public AvgTimeServiceImpl(AvgtimeMapper mapper,
                              BusdataMapper busdataMapper) {
        this.mapper = mapper;
        this.busdataMapper = busdataMapper;
    }

    @Override
    public Avgtime getAvgTimeByTest(String startStation, String endStation, String tableName) {
        return mapper.selectByPrimaryKey(startStation, endStation, tableName);
    }

    @Override
    public Busdata getBusdataByCondition(String lineNo, String startStation,
                                         String endStation, String direction,
                                         String period) {
        return busdataMapper.selectByCondition(lineNo, startStation, endStation, direction, period);
    }

    @Override
    public List<Busdata> getBusdataWithoutPeriod(String lineNo, String startStation,
                                                 String endStation, String direction) {
        return null;
    }
}
