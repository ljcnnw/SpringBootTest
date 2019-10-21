package com.testspringboot.demo.config;


import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * shiro配置
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        System.out.println("=======Shiro拦截器========");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //未登录接口
        shiroFilterFactoryBean.setLoginUrl("/pub/needLogin");
        //未授权调用
        shiroFilterFactoryBean.setUnauthorizedUrl("/pub/noPermit");
        //一定要用LinkMap
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //推出过滤器
        filterChainDefinitionMap.put("/logout", "logout");
        //匿名
        filterChainDefinitionMap.put("/pub/**", "anon");
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/v2/**", "anon");
        //登录访问
        filterChainDefinitionMap.put("/authc/**", "authc");
        filterChainDefinitionMap.put("/user/**", "authc");
        //管理员访问
        filterChainDefinitionMap.put("/admin/**", "roles[admin]");

        filterChainDefinitionMap.put("/aaa/**", "perms[update]");

        //过滤器是顺序执行，一般把/**放到最下面，所以要用linkMap
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * shiro管理类
     * 安全管理
     * 需要设置 realm，SessionManager
     *
     * @return
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setSessionManager(sessionManager());
        defaultWebSecurityManager.setRealm(customRealm());
        return defaultWebSecurityManager;
    }

    @Bean
    public CustomRealm customRealm() {
        CustomRealm customRealm = new CustomRealm();
        // customRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return customRealm;
    }

    /**
     * 加密处理
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //设置密码加密规则
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        //散列次数，相当于两次md5加密
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }


    /**
     * 自定义session
     *
     * @return
     */
    @Bean
    public SessionManager sessionManager() {
        CustomSessionManager customSessionManager = new CustomSessionManager();
        //会话超时时间，默认30分钟，单位是毫秒
        //customSessionManager.setGlobalSessionTimeout(20000);
        return customSessionManager;
    }
}
