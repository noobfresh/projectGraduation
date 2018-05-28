package com.cqu.project.graduation.service;

import com.cqu.project.graduation.entity.Statistic;

import java.util.List;

public interface IStatService {

    Statistic selectByTableName(String tableName);
}
