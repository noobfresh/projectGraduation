package com.cqu.project.graduation.service;

import com.cqu.project.graduation.entity.Od;
import com.cqu.project.graduation.mapper.OdMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaculateAdminServiceImpl implements ICaculatAdminService {

    private OdMapper odMapper;

    @Autowired
    public CaculateAdminServiceImpl(OdMapper odMapper) {
        this.odMapper = odMapper;
    }


    @Override
    public int countODByDay(String date) {
        String tableName = "od" + date;
        return odMapper.countByDate(tableName);
    }

    @Override
    public List<Od> top5ODByTable(String tableName) {
        return odMapper.top5ODsByTable(tableName);
    }
}
