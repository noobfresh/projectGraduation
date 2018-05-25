package com.cqu.project.graduation.service;

import com.cqu.project.graduation.entity.Od;

import java.util.List;

public interface ICaculatAdminService {

    int countODByDay(String date);

    List<Od> top5ODByTable(String tableName);
}
