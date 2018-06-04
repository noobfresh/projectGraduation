package com.cqu.project.graduation.controller;


import com.cqu.project.graduation.entity.*;
import com.cqu.project.graduation.service.*;
import com.cqu.project.graduation.util.TimeGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Time;
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
    private IStatService statService;
    private IBusStationService busStationService;
    private IPredictService predictService;

    @Autowired
    public CaculteAdminController(ICaculatAdminService caculatAdminService,
                                  IRailwayStationService railwayStationService,
                                  IAvgTimeService avgTimeService,
                                  IStatService statService,
                                  IBusStationService busStationService,
                                  IPredictService predictService) {
        this.caculatAdminService = caculatAdminService;
        this.railwayStationService = railwayStationService;
        this.avgTimeService = avgTimeService;
        this.statService = statService;
        this.busStationService = busStationService;
        this.predictService = predictService;
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
            String tableName = "avgtime20170901" + startTime + endTime;
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

    @RequestMapping("/statday")
    @ResponseBody
    public Map statDay(String date){
        Map<String, Object> map = new HashMap<>();
        List<TempStructure> list = new ArrayList<>();
        String startTime = "0600";
        //毕竟我只算了一天的
        date = "20170902";
        while (!startTime.equals("0000")){
            String endTime = TimeGeneratorUtil.timeIncrement(startTime);
            String tableName = "avgtime" + date + startTime + endTime;
            list.add(new TempStructure(startTime + "~" + endTime,
                    statService.selectByTableName(tableName)));
            startTime = endTime;
        }

        map.put("result", list);
        return map;
    }

    @RequestMapping("/statweek")
    @ResponseBody
    public Map statWeek(String startDate, String endDate){
        Map<String, Object> map = new HashMap<>();
        //默认了传进来的数值是正确范围
        int start = Integer.valueOf(startDate.substring(6));
        int end = Integer.valueOf(endDate.substring(6));

        List<TempStructure> list = new ArrayList<>();
        for(int i = start; i < end + 1; i++){
            String tableName = "avgtime201709" + TimeGeneratorUtil.convertLess10Num(i);
            list.add(new TempStructure("201709" + TimeGeneratorUtil.convertLess10Num(i),
                    statService.selectByTableName(tableName)));
        }
        map.put("result", list);
        return map;
    }

    @RequestMapping("busdataday")
    @ResponseBody
    public Map busdataDay(String date, String lineNo, String direction){
        Map<String, Object> map = new HashMap<>();
        List<TempStructure> morningData = new ArrayList<>();
        List<TempStructure> eveningData = new ArrayList<>();
        List<TempStructure> otherData = new ArrayList<>();
        String tableName = "busdata" + date.substring(4);
        //判断方向 到站点表取出对应的站点列表
        List<Busstation> busstations = busStationService.selectByLineNoDirection(lineNo, direction);
        for(int i = 0; i < busstations.size() - 1; i++){
            String startStation = busstations.get(i).getStationName();
            String endStation = busstations.get(i + 1).getStationName();
            List<Busdata> busdatas = avgTimeService.getBusdataWithoutPeriod(lineNo,
                    startStation, endStation, direction, tableName);
            for(Busdata temp : busdatas){
                if(temp.getPeriod().equals("morning")){
                    morningData.add(new TempStructure(temp));
                } else if(temp.getPeriod().equals("evening")) {
                    eveningData.add(new TempStructure(temp));
                } else {
                    otherData.add(new TempStructure(temp));
                }
            }
        }

        map.put("morning", morningData);
        map.put("evening", eveningData);
        map.put("other", otherData);
        return map;
    }

    @RequestMapping("busdataweek")
    @ResponseBody
    public Map busdataWeek(String startDate, String endDate,
                           String period, String lineNo, String direction){
        Map<String, Object> map = new HashMap<>();

        //判断方向 到站点表取出对应的站点列表
        List<Busstation> busstations = busStationService.selectByLineNoDirection(lineNo, direction);
        int startIndex = Integer.valueOf(startDate.substring(6));
        int endIndex = Integer.valueOf(endDate.substring(6));

        List<List<TempStructure>> finalResults = new ArrayList<>();
        for(int i = startIndex; i < endIndex + 1; i++){
            String tableName = "busdata05" + TimeGeneratorUtil.convertLess10Num(i);
            List<TempStructure> busdatas = new ArrayList<>();
            for(int j = 0; j < busstations.size() - 1; j++){
                String startStation = busstations.get(j).getStationName();
                String endStation = busstations.get(j + 1).getStationName();
                busdatas.add(new TempStructure("201405" + TimeGeneratorUtil.convertLess10Num(i),
                        avgTimeService.getBusdataByTableName(lineNo,
                        startStation, endStation, direction, period, tableName)));
            }
            finalResults.add(busdatas);
        }

        map.put("result", finalResults);
        return map;
    }


    @RequestMapping("lstmday")
    @ResponseBody
    public Map lstmDay(String date, String startStationName, String endStationName){
        Map<String, Object> map = new HashMap<>();
        date = "20170922";
        Railwaystation start = railwayStationService.selectByName(startStationName);
        Railwaystation end = railwayStationService.selectByName(endStationName);
        List<TempStructure> list = new ArrayList<>();
        String startTime = "0600";
        while (!startTime.equals("0000")){
            String endTime = TimeGeneratorUtil.timeIncrement(startTime);
            String tableName = "predict_avgtime_" + startTime + endTime;
            //导入时候少了开头的0尴尬了
            PredictAvgtime temp = predictService.selectByPrimary(start.getId().substring(1),
                    end.getId().substring(1), tableName);
            list.add(new TempStructure(startTime + "~" + endTime,temp
                    ));
            startTime = endTime;
        }
        map.put("result", list);
        return map;
    }

    @RequestMapping("lstmweek")
    @ResponseBody
    public Map lstmWeek(String startDate, String endDate, String startStationName, String endStationName){
        Map<String, Object> map = new HashMap<>();
        startDate = "20170922";
        endDate = "20170928";
        Railwaystation start = railwayStationService.selectByName(startStationName);
        Railwaystation end = railwayStationService.selectByName(endStationName);
        List<TempStructure> list = new ArrayList<>();

        map.put("result", predictService.selectByTimeRange(startDate, endDate,
                start.getId().substring(1), end.getId().substring(1)));
        return map;
    }

    @RequestMapping("lstmbusday")
    @ResponseBody
    public Map lstmBusDay(String date, String lineNo, String period, String direction){
        if(period.equals("早")){
            period = "morning";
        } else if(period.equals("晚")){
            period = "evening";
        } else {
            period = "other";
        }
        Map<String, Object> map = new HashMap<>();
        date = "0526";
        List<Busstation> busstations = busStationService.selectByLineNoDirection(lineNo, "R");
        List<TempStructure> list = new ArrayList<>();
        for(int i = 0; i < busstations.size() - 1; i++){
            String startStation = busstations.get(i).getStationName();
            String endStation = busstations.get(i + 1).getStationName();
            PredictBusDay temp = predictService.selectByPrimaryBus(lineNo, startStation, endStation, date, period);
            list.add(new TempStructure(date, temp));
        }

        map.put("result", list);
        return map;
    }


    @RequestMapping("lstmbusweek")
    @ResponseBody
    public Map lstmBusWeek(String startDate, String endDate, String lineNo, String period, String direction,
                           String startStation, String endStation){
        Map<String, Object> map = new HashMap<>();
        startDate = "0526";
        endDate = "0531";
        List<Busstation> busstations = busStationService.selectByLineNoDirection(lineNo, "R");
        List<TempStructure> list = new ArrayList<>();
//        for(int i = 0; i < busstations.size() - 1; i++){
//            String startStation = busstations.get(i).getStationName();
//            String endStation = busstations.get(i + 1).getStationName();
//            PredictBusDay temp = predictService.selectByPrimaryBus(lineNo, startStation, endStation, date, period);
//            list.add(new TempStructure(date, temp));
//        }


        map.put("result", predictService.selecByTimeRange(startDate, endDate,
                startStation, endStation, lineNo));
        return map;
    }

    class TempStructure{
        public Avgtime avgtime;
        public String timeRange;
        public Statistic statistic;
        public Busdata busdata;
        public PredictAvgtime predictAvgtime;
        public PredictAvgtimeWeek predictAvgtimeWeek;
        public PredictBusDay predictBusDay;
        public PredictBusWeek predictBusWeek;


        public TempStructure(String timeRange, PredictBusDay predictBusDay) {
            this.timeRange = timeRange;
            this.predictBusDay = predictBusDay;
        }

        public TempStructure(String timeRange, PredictAvgtime predictAvgtime) {
            this.timeRange = timeRange;
            this.predictAvgtime = predictAvgtime;
        }

        public TempStructure(Avgtime avgtime, String timeRange) {
            this.avgtime = avgtime;
            this.timeRange = timeRange;
        }

        public TempStructure(String timeRange, Statistic statistic) {
            this.timeRange = timeRange;
            this.statistic = statistic;
        }

        public TempStructure(Busdata busdata) {
            this.busdata = busdata;
        }

        public TempStructure(String timeRange, Busdata busdata) {
            this.timeRange = timeRange;
            this.busdata = busdata;
        }

        public TempStructure() {
        }
    }
}
