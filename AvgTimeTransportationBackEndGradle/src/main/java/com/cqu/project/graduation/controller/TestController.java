package com.cqu.project.graduation.controller;

import com.cqu.project.graduation.entity.Avgtime06000610;
import com.cqu.project.graduation.service.IAvgTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

    private IAvgTimeService avgTimeService;

    @Autowired
    public TestController(IAvgTimeService avgTimeService) {
        this.avgTimeService = avgTimeService;
    }

    @RequestMapping("/showTest")
    public String toIndex(String startStation, String endStation) {
        Avgtime06000610 test = avgTimeService.getAvgTimeByTest(startStation, endStation);
        return String.valueOf(test.getDuration());
    }
}
