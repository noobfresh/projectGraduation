package com.cqu.graduation.pyf.graduationprojectavgtraveltime.bean;

/**
 * Created by PYF on 2018/5/19.
 */

public class UserBean {

    private String name;
    private int age;

    public UserBean(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public UserBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
