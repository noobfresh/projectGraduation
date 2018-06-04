package com.cqu.project.graduation.service;

import com.cqu.project.graduation.entity.PredictAvgtime;
import com.cqu.project.graduation.entity.PredictAvgtimeWeek;
import com.cqu.project.graduation.entity.PredictBusDay;
import com.cqu.project.graduation.entity.PredictBusWeek;
import com.cqu.project.graduation.mapper.PredictAvgtimeMapper;
import com.cqu.project.graduation.mapper.PredictAvgtimeWeekMapper;
import com.cqu.project.graduation.mapper.PredictBusDayMapper;
import com.cqu.project.graduation.mapper.PredictBusWeekMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PredictServiceImpl implements IPredictService {

    private PredictAvgtimeMapper predictAvgtimeMapper;
    private PredictAvgtimeWeekMapper predictAvgtimeWeekMapper;
    private PredictBusDayMapper predictBusDayMapper;
    private PredictBusWeekMapper predictBusWeekMapper;


    @Autowired
    public PredictServiceImpl(PredictAvgtimeMapper predictAvgtimeMapper,
                              PredictAvgtimeWeekMapper predictAvgtimeWeekMapper,
                              PredictBusDayMapper predictBusDayMapper,
                              PredictBusWeekMapper predictBusWeekMapper) {
        this.predictAvgtimeMapper = predictAvgtimeMapper;
        this.predictAvgtimeWeekMapper = predictAvgtimeWeekMapper;
        this.predictBusDayMapper = predictBusDayMapper;
        this.predictBusWeekMapper = predictBusWeekMapper;
    }

    @Override
    public PredictAvgtime selectByPrimary(String startStation, String endStation, String tableName) {
        return predictAvgtimeMapper.selectByPrimaryKey(startStation, endStation, tableName);
    }

    @Override
    public List<PredictAvgtimeWeek> selectByTimeRange(String startDate, String endDate,
                                                      String startStation, String endStation) {
        List<PredictAvgtimeWeek> list = new ArrayList<>();
        //默认了 22-28
        for(int i = 22; i < 29; i++){
            String date = "201709" + i;
            list.add(predictAvgtimeWeekMapper.selectByPrimaryKey(startStation, endStation, date));
        }
        return list;
    }

    @Override
    public PredictBusDay selectByPrimaryBus(String lineNo, String startStation,
                                            String endStation, String date, String period) {
        date = "0526";
        return predictBusDayMapper.selectByPrimaryKey(lineNo, startStation, endStation, date, period);
    }

    @Override
    public List<PredictBusWeek> selecByTimeRange(String startDate, String endDate,
                                                 String startStation, String endStation,
                                                 String lineNo) {
        //默认26-31
        List<PredictBusWeek> list = new ArrayList<>();
        for(int i = 26; i < 32; i++){
            String date = "05" + i;
            list.add(predictBusWeekMapper.selectByPrimaryKey(lineNo, startStation, endStation, date));
        }
        return list;
    }
}
