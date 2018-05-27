package com.cqu.project.graduation.controller;


import com.cqu.project.graduation.entity.Avgtime;
import com.cqu.project.graduation.entity.Od;
import com.cqu.project.graduation.entity.Railwaystation;
import com.cqu.project.graduation.service.IAvgTimeService;
import com.cqu.project.graduation.service.ICaculatAdminService;
import com.cqu.project.graduation.service.IRailwayStationService;
import com.cqu.project.graduation.util.TimeGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class CaculteAdminController {

    private ICaculatAdminService caculatAdminService;
    private IRailwayStationService railwayStationService;
    private IAvgTimeService avgTimeService;

    @Autowired
    public CaculteAdminController(ICaculatAdminService caculatAdminService,
                                  IRailwayStationService railwayStationService,
                                  IAvgTimeService avgTimeService) {
        this.caculatAdminService = caculatAdminService;
        this.railwayStationService = railwayStationService;
        this.avgTimeService = avgTimeService;
    }

    @RequestMapping("/countDayODs")
    @ResponseBody
    public Map countDayOds(String date){
        Map<String, String> map = new HashMap<>();
        int count = caculatAdminService.countODByDay(date);
        map.put("count", String.valueOf(count));
        map.put("date", "20170901");
        return map;
    }

    @RequestMapping("/top5OD")
    @ResponseBody
    public Map top5OD(){
        Map<String, List<Od>> map = new HashMap<>();
        for(int i = 1; i < 8; i++){
            String tableName = "od2017090" + i;
            map.put(tableName, caculatAdminService.top5ODByTable(tableName));
        }
        return map;
    }

    @RequestMapping("/avgtimeday")
    @ResponseBody
    public Map askForAvgTimeDay(String startStationName, String endStationName){
        Map<String, Object> map = new HashMap<>();
        Railwaystation start = railwayStationService.selectByName(startStationName);
        Railwaystation end = railwayStationService.selectByName(endStationName);
        String startTime = "0600";
        List<TempStructure> list = new ArrayList<>();
        while (!startTime.equals("0000")){
            String endTime = TimeGeneratorUtil.timeIncrement(startTime);
            String tableName = "avgtime" + startTime + endTime;
            Avgtime time = avgTimeService.getAvgTimeByTest(start.getId(), end.getId(), tableName);
            list.add(new TempStructure(time, startTime + "~" + endTime));
            startTime = endTime;
        }
        map.put("result", list);
        return map;
    }

    @RequestMapping("/avgtimeweek")
    @ResponseBody
    public Map askForAvgTimeWeek(String startStationName, String endStationName){
        Map<String, Object> map = new HashMap<>();
        Railwaystation start = railwayStationService.selectByName(startStationName);
        Railwaystation end = railwayStationService.selectByName(endStationName);
        List<TempStructure> list = new ArrayList<>();

        //原谅我直接默认了
        for(int i = 4; i < 11; i++){
            String tableName = "avgtime201709" + TimeGeneratorUtil.convertLess10Num(i);
            Avgtime time = avgTimeService.getAvgTimeByTest(start.getId(), end.getId(), tableName);
            list.add(new TempStructure(time, "20170904~20170910"));
        }
        map.put("result", list);
        return map;
    }

    class TempStructure{
        public Avgtime avgtime;
        public String timeRange;

        public TempStructure(Avgtime avgtime, String timeRange) {
            this.avgtime = avgtime;
            this.timeRange = timeRange;
        }

        public TempStructure() {
        }
    }
}
