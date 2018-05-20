package com.cqu.project.graduation.service;


import com.cqu.project.graduation.entity.Avgtime;
import com.cqu.project.graduation.mapper.AvgtimeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AvgTimeServiceImpl implements IAvgTimeService{

    private AvgtimeMapper mapper;

    @Autowired
    public AvgTimeServiceImpl(AvgtimeMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Avgtime getAvgTimeByTest(String startStation, String endStation, String tableName) {
        return mapper.selectByPrimaryKey(startStation, endStation, tableName);
    }
}
