package com.cqu.project.graduation.service;

import com.cqu.project.graduation.entity.Railwaystation;
import com.cqu.project.graduation.mapper.RailwaystationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RailwayStationServiceImpl implements IRailwayStationService {

    private RailwaystationMapper railwaystationMapper;

    @Autowired
    public RailwayStationServiceImpl(RailwaystationMapper railwaystationMapper) {
        this.railwaystationMapper = railwaystationMapper;
    }

    @Override
    public Railwaystation selectByName(String stationName) {
        return railwaystationMapper.selectByPrimaryKey(stationName);
    }
}
