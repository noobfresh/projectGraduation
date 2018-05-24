package com.cqu.project.graduation.controller;


import com.cqu.project.graduation.entity.Avgtime;
import com.cqu.project.graduation.entity.Railwaystation;
import com.cqu.project.graduation.service.IAvgTimeService;
import com.cqu.project.graduation.service.IRailwayStationService;
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
    private IRailwayStationService railwayStationService;

    @Autowired
    public CaculateTimeAndroidController(IAvgTimeService avgTimeService,
                                         IRailwayStationService railwayStationService) {
        this.avgTimeService = avgTimeService;
        this.railwayStationService = railwayStationService;
    }


    @RequestMapping("/railwayCaculate")
    @ResponseBody
    public Map railwayAvgTimeCaculate(String startStation,
                                      String endStation,
                                      String startTime, String weekday) {
        Map<String, String> map = new HashMap<>();
        try {
            String tableName = "avgtime" + startTime + TimeGeneratorUtil.timeIncrement(startTime);
            //传进来的站点是中文，需要查两次，转换一波
            Railwaystation start = railwayStationService.selectByName(startStation);
            Railwaystation end = railwayStationService.selectByName(endStation);
            Avgtime test = null;
            if(start != null && end != null){
                String startId = start.getId();
                String endId = end.getId();
                test = avgTimeService.getAvgTimeByTest(startId, endId,tableName);
            }

            map.put("startStation", test != null ? test.getStartStation() : "");
            map.put("endStation", test != null ? test.getEndStation(): "");
            map.put("direction", test != null ? test.getDirection(): "");
            map.put("duration", test != null ? String.valueOf(test.getDuration()): "0");
            map.put("distance", test != null ? String.valueOf(test.getDistance()): "0.0");
            map.put("time", test != null ? test.getTime(): "");
            map.put("isbus", test != null ? test.getIsbus(): "");
        } catch (Exception e){
            e.printStackTrace();
        }
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
