package com.testspringboot.demo.User.Entity;

import io.swagger.annotations.ApiOperation;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

/**
 * 用户类
 */

public class User {
    @Id
    private int id;
    @NotEmpty(message = "用户名不能为空")
    private String userName;
    @NotEmpty(message = "密码不能为空")
    private String password;
    private List<Role> roleList;
    @Valid
    private UserInfo userInfo;

    private String emailCheckNum;//邮箱验证码


    public String getEmailCheckNum() {
        return emailCheckNum;
    }

    public void setEmailCheckNum(String emailCheckNum) {
        this.emailCheckNum = emailCheckNum;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
}
