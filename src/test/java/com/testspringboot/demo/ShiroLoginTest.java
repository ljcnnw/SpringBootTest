package com.testspringboot.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

public class ShiroLoginTest {
    private SimpleAccountRealm accountRealm = new SimpleAccountRealm();

    private DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

    @Before
    public void init(){

        accountRealm.addAccount("test1","123");
        accountRealm.addAccount("test2","123");

        defaultSecurityManager.setRealm(accountRealm);

    }

    @Test
    public void testLogin(){
        SecurityUtils.setSecurityManager(defaultSecurityManager);

        //操作主题
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("test1","123");

        subject.login(usernamePasswordToken);

        System.out.println(subject.isAuthenticated());

    }
}
