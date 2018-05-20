package com.cqu.project.graduation.controller;


import com.cqu.project.graduation.entity.Avgtime;
import com.cqu.project.graduation.service.IAvgTimeService;
import com.cqu.project.graduation.util.TimeGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/android")
public class CaculateTimeAndroidController {

    private IAvgTimeService avgTimeService;

    @Autowired
    public CaculateTimeAndroidController(IAvgTimeService avgTimeService) {
        this.avgTimeService = avgTimeService;
    }

    @RequestMapping("/railwayCaculate")
    @ResponseBody
    public Map railwayAvgTimeCaculate(String startStation,
                                      String endStation,
                                      String startTime, String weekday) {
        String tableName = "avgtime" + startTime + TimeGeneratorUtil.timeIncrement(startTime);
        Avgtime test = avgTimeService.getAvgTimeByTest(startStation, endStation,tableName);
        Map<String, String> map = new HashMap<>();
        map.put("duration", String.valueOf(test.getDuration()));
        return map;
    }

    @RequestMapping("/busCaculate")
    @ResponseBody
    public Map<String, String> busAvgTimeCaculte(String lineNo, String startStation,
                                                 String endStation, String period,
                                                 String weekday, String startTime){
        Map<String, String > map = new HashMap<>();

        //站点表

        return map;
    }
}
