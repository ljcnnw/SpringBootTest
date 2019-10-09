package com.testspringboot.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

public class NewRealmTest {
    private CustomRealm customRealm = new CustomRealm();
    private DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();


    @Before
    public void init(){
        defaultSecurityManager.setRealm(customRealm);
        SecurityUtils.setSecurityManager(defaultSecurityManager);
    }

    @Test
    public void test(){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("jack","123");
        //登录
        subject.login(usernamePasswordToken);

        //是否登录
        System.out.println(subject.isAuthenticated());

        subject.checkRole("role1");

        System.out.println("权限=====");
    }
}
