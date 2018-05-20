package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Busdata;
import java.util.List;

public interface BusdataMapper {
    int insert(Busdata record);

    List<Busdata> selectAll();
}