package com.cqu.project.graduation.service;

import com.cqu.project.graduation.entity.Busstation;
import com.cqu.project.graduation.mapper.BusstationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusStationServiceImpl implements IBusStationService {

    private BusstationMapper busstationMapper;


    @Autowired
    public BusStationServiceImpl(BusstationMapper busstationMapper) {
        this.busstationMapper = busstationMapper;
    }

    @Override
    public Busstation selectByPrimaryKey(String id, String lineNo) {
        return busstationMapper.selectByPrimaryKey(id, lineNo);
    }

    @Override
    public List<Busstation> selectByStationName(String station, String lineNo) {
        return busstationMapper.selectByStationName(station, lineNo);
    }

    @Override
    public List<Busstation> selectByStationName(String station) {
        return busstationMapper.selectOnlyByStationName(station);
    }


    /**
     *
     * @param station
     * @param lineNo
     * @return true  ---- 正方向
     *          false ---- 反方向
     */
    @Override
    public boolean isDirection(String station, String lineNo) {
        List<Busstation> busstations = busstationMapper.selectByStationName(station, lineNo);
        for(Busstation temp : busstations){
            if(temp.getId().equals("1")){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Busstation> selectByRange(int startId, int endId, String lineNo) {
        return busstationMapper.selectByRange(startId, endId, lineNo);
    }
}