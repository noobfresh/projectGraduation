package com.cqu.project.graduation.service;

import com.cqu.project.graduation.entity.Railwaystation;

public interface IRailwayStationService {

    Railwaystation selectByName(String stationName);
}
