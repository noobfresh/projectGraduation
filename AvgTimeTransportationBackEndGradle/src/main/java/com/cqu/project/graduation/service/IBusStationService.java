package com.cqu.project.graduation.service;

import com.cqu.project.graduation.entity.Busstation;

import java.util.List;

public interface IBusStationService {

    Busstation selectByPrimaryKey(String id, String lineNo);

    List<Busstation> selectByStationName(String station, String lineNo);

    List<Busstation> selectByStationName(String station);

    boolean isDirection(String station, String lineNo);

    List<Busstation> selectByRange(int startId, int endId, String lineNo);

    List<Busstation> selectByLineNoDirection(String lineNo, String Direction);
}
