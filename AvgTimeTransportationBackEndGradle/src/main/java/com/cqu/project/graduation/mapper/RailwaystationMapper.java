package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Railwaystation;
import java.util.List;

public interface RailwaystationMapper {
    int deleteByPrimaryKey(String stationName);

    int insert(Railwaystation record);

    Railwaystation selectByPrimaryKey(String stationName);

    List<Railwaystation> selectAll();

    int updateByPrimaryKey(Railwaystation record);
}