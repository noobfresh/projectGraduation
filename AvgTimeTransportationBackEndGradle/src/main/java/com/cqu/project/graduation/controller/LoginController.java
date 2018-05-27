package com.cqu.project.graduation.controller;


import com.cqu.project.graduation.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController {

    private ILoginService loginService;

    @Autowired
    public LoginController(ILoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping("/vertify")
//    @ResponseBody
    public String login(String username, String password){
        Map<String, String> map = new HashMap<>();
        map.put("page", loginService.vertify(username, password) ? "admin" : "index");
//        return map;
        return loginService.vertify(username, password) ? "admin" : "index";
    }
}
