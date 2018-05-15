package com.cqu.project.graduation.service;

import com.cqu.project.graduation.entity.Avgtime06000610;

public interface IAvgTimeService {

    Avgtime06000610 getAvgTimeByTest(String startStation, String endStation);
}
