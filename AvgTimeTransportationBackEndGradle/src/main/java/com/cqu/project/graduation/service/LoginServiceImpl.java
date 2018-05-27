package com.cqu.project.graduation.service;

import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements ILoginService {
    @Override
    public boolean vertify(String username, String password) {
        if(username.equals("954078940@qq.com")){
            if(password.equals("123456")){
                return true;
            }
        }
        return false;
    }
}
