package com.testspringboot.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class JdbcRealmTest {
    @Test
    public void test1(){

        //初始化配置文件 读取配置
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:jdbcrealm.ini");

        SecurityManager securityManager = factory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("jack","123");
        //登录
        subject.login(usernamePasswordToken);

        //是否登录
        System.out.println(subject.isAuthenticated());
        //是否有对应的角色
        System.out.println(subject.hasRole("user"));
        //获取用户名
        System.out.println(subject.getPrincipal());

        System.out.println(subject.isPermitted("role1"));
        //推出
        subject.logout();
        System.out.println(subject.isAuthenticated());


    }
}
