package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Railwaystation;
import java.util.List;

public interface RailwaystationMapper {
    int deleteByPrimaryKey(String id);

    int insert(Railwaystation record);

    Railwaystation selectByPrimaryKey(String id);

    List<Railwaystation> selectAll();

    int updateByPrimaryKey(Railwaystation record);
}