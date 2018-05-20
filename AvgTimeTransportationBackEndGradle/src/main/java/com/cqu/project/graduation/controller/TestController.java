package com.cqu.project.graduation.controller;


import com.cqu.project.graduation.entity.Avgtime;
import com.cqu.project.graduation.service.IAvgTimeService;
import com.cqu.project.graduation.util.TimeGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/test")
public class TestController {

    private IAvgTimeService avgTimeService;

    @Autowired
    public TestController(IAvgTimeService avgTimeService) {
        this.avgTimeService = avgTimeService;
    }


}
