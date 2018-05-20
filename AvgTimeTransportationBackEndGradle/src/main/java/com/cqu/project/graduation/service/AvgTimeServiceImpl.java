package com.cqu.project.graduation.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AvgTimeServiceImpl implements IAvgTimeService{

    private Avgtime06000610Mapper mapper;

    @Autowired
    public AvgTimeServiceImpl(Avgtime06000610Mapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Avgtime06000610 getAvgTimeByTest(String startStation, String endStation) {
        return mapper.selectByPrimaryKey(startStation, endStation);
    }
}
