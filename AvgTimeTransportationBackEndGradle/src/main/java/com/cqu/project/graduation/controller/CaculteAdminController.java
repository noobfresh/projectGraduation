package com.cqu.project.graduation.controller;


import com.cqu.project.graduation.entity.Od;
import com.cqu.project.graduation.service.ICaculatAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class CaculteAdminController {

    private ICaculatAdminService caculatAdminService;

    @Autowired
    public CaculteAdminController(ICaculatAdminService caculatAdminService) {
        this.caculatAdminService = caculatAdminService;
    }

    @RequestMapping("/countDayODs")
    @ResponseBody
    public Map countDayOds(String date){
        Map<String, String> map = new HashMap<>();
        int count = caculatAdminService.countODByDay(date);
        map.put("count", String.valueOf(count));
        map.put("date", "20170901");
        return map;
    }

    @RequestMapping("/top5OD")
    @ResponseBody
    public Map top5OD(){
        Map<String, List<Od>> map = new HashMap<>();
        for(int i = 1; i < 8; i++){
            String tableName = "od2017090" + i;
            map.put(tableName, caculatAdminService.top5ODByTable(tableName));
        }
        return map;
    }
}
