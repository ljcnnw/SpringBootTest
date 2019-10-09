package com.testspringboot.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

public class ShiroLoginTest2 {
    private SimpleAccountRealm accountRealm = new SimpleAccountRealm();

    private DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

    @Before
    public void init(){

        accountRealm.addAccount("test1","123","root","admin");
        accountRealm.addAccount("test2","123","user");

        defaultSecurityManager.setRealm(accountRealm);

    }

    @Test
    public void testLogin(){
        SecurityUtils.setSecurityManager(defaultSecurityManager);

        //操作主题
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("test1","123");
        //登录
        subject.login(usernamePasswordToken);
        //是否登录
        System.out.println(subject.isAuthenticated());
        //是否有对应的角色
        System.out.println(subject.hasRole("root"));
        //获取用户名
        System.out.println(subject.getPrincipal());
        //推出
        subject.logout();
        System.out.println(subject.isAuthenticated());




    }
}
