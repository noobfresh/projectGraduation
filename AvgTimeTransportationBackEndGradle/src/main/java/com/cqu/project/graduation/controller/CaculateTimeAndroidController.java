package com.cqu.project.graduation.controller;


import com.cqu.project.graduation.entity.Avgtime;
import com.cqu.project.graduation.entity.Busdata;
import com.cqu.project.graduation.entity.Busstation;
import com.cqu.project.graduation.entity.Railwaystation;
import com.cqu.project.graduation.service.IAvgTimeService;
import com.cqu.project.graduation.service.IBusStationService;
import com.cqu.project.graduation.service.IRailwayStationService;
import com.cqu.project.graduation.util.TimeGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/android")
public class CaculateTimeAndroidController {

    private static final String BUS_DIRECTION_R = "R";
    private static final String BUS_DIRECTION_F = "F";
    private static final String BUS_PERIOD_MORNING = "morning";
    private static final String BUS_PERIOD_EVENING = "evening";
    private static final String BUS_PERIOD_OTHER = "other";

    private IAvgTimeService avgTimeService;
    private IRailwayStationService railwayStationService;
    private IBusStationService busStationService;

    @Autowired
    public CaculateTimeAndroidController(IAvgTimeService avgTimeService,
                                         IRailwayStationService railwayStationService,
                                         IBusStationService busStationService) {
        this.avgTimeService = avgTimeService;
        this.railwayStationService = railwayStationService;
        this.busStationService = busStationService;
    }


    @RequestMapping("/railwayCaculate")
    @ResponseBody
    public Map railwayAvgTimeCaculate(String startStation,
                                      String endStation,
                                      String startTime, String weekday) {
        Map<String, String> map = new HashMap<>();
        try {
            //注意若时间不在运营范围内，则直接返回0
            Avgtime test = null;
            int startTimeInt = Integer.valueOf(startTime);

            if(startTimeInt >= 600 && startTimeInt <= 2359){
                String tableName = "avgtime" + startTime + TimeGeneratorUtil.timeIncrement(startTime);
                //传进来的站点是中文，需要查两次，转换一波
                Railwaystation start = railwayStationService.selectByName(startStation);
                Railwaystation end = railwayStationService.selectByName(endStation);



                //轨交的判断
                if(start != null && end != null){
                    String startId = start.getId();
                    String endId = end.getId();
                    test = avgTimeService.getAvgTimeByTest(startId, endId,tableName);
                }


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
                                                 String endStation, String weekday,
                                                 String startTime, String startDirection){
        Map<String, String > map = new HashMap<>();

        //站点表

        //公交的判断
        //如果是公交这个旅程时间好麻烦啊
        //有个问题哈，怎么判断公交车的方向呢？
        String direction = busStationService.isDirection(startDirection, lineNo) ?
                BUS_DIRECTION_R : BUS_DIRECTION_F;
        //时间段判断，7-9 早高峰， 17-19 晚高峰
        int startTimeInt = Integer.valueOf(startTime);
        String peroid = BUS_PERIOD_OTHER;
        if((startTimeInt >= 700) && (startTimeInt <= 900)){
            peroid = BUS_PERIOD_MORNING;
        }
        if(startTimeInt >= 1700 && startTimeInt <= 1900){
            peroid = BUS_PERIOD_EVENING;
        }
        //
        Busdata time = new Busdata();
        List<Busstation> startList = busStationService.selectByStationName(startStation, lineNo);
        List<Busstation> endList = busStationService.selectByStationName(endStation, lineNo);

        //防止不存在
        if(startList != null && endList != null &&
                startList.size() > 0 && endList.size() > 0){
            Busstation startBusstation = selectCorrectOne(startList, direction);
            Busstation endBusstation = selectCorrectOne(endList, direction);

            map.put("date", "0505");
            map.put("direction", direction);
            map.put("lineNo", lineNo);
            map.put("period", peroid);
            map.put("startStation", startBusstation.getStationName());
            map.put("endStation", endBusstation.getStationName());
            //

            List<Busstation> viaStation = busStationService.selectByRange(
                    Integer.valueOf(startBusstation.getId()),
                    Integer.valueOf(endBusstation.getId()), lineNo);
            //取出的结果不是根据id有序，需重新排序一遍
            Collections.sort(viaStation, new Comparator<Busstation>() {
                @Override
                public int compare(Busstation o1, Busstation o2) {
                    if(Integer.valueOf(o1.getId()) > Integer.valueOf(o2.getId())){
                        return 1;
                    } else  if(Integer.valueOf(o1.getId()) == Integer.valueOf(o2.getId())){
                        return 0;
                    } else {
                        return -1;
                    }
                }
            });
            int duration= 0;
            for(int i = 0; i < viaStation.size() - 1; i++){
                duration += avgTimeService.getBusdataByCondition(lineNo,
                        viaStation.get(i).getStationName(),
                        viaStation.get(i+1).getStationName(),
                        direction, peroid)
                        .getDuration();
            }
            map.put("duration", String.valueOf(duration));
        }
        else {
            //感觉这样写好蠢啊，其实可以直接让函数返回一个对象，自动将对象解成json，不用用map
            map.put("date", "");
            map.put("direction", "");
            map.put("lineNo", "");
            map.put("period", "");
            map.put("startStation", "");
            map.put("endStation", "");
            map.put("duration", "");
        }
        return map;
    }


    public Busstation selectCorrectOne(List<Busstation> busstations, String direction){
        if(busstations.size() == 1){
            return busstations.get(0);
        }

        //判断方向，正方向，选id小的站点；反方向，选id大的站点
        if(direction.equals(BUS_DIRECTION_R)){
            Busstation result = busstations.get(0);
            for(Busstation temp : busstations){
                if(Integer.valueOf(temp.getId()) < Integer.valueOf(result.getId())){
                    result = temp;
                }
            }
            return result;
        }else {
            Busstation result = busstations.get(0);
            for(Busstation temp : busstations){
                if(Integer.valueOf(temp.getId()) > Integer.valueOf(result.getId())){
                    result = temp;
                }
            }
            return result;
        }
    }
}
