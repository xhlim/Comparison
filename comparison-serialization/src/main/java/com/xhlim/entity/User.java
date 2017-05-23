package com.xhlim.entity;

import java.io.Serializable;

/**
 * Created by xhlim on 2017/5/5.
 */
public class User implements Serializable {

    private String userName;
    private String password;
    private int age;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
