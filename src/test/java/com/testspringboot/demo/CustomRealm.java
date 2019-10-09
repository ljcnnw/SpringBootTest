package com.testspringboot.demo;

import com.alibaba.druid.util.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {

    private final Map<String, String> userInfo = new HashMap<>();

    {
        userInfo.put("jack", "123");
        userInfo.put("test", "123");
    }


    private final Map<String, Set<String>> permissionInfo = new HashMap<>();

    {
        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();
        set1.add("video:find");
        set1.add("video:buy");
        set2.add("video:add");
        set2.add("video:delete");
        permissionInfo.put("jack", set1);
        permissionInfo.put("test", set2);
    }

    private final Map<String, Set<String>> roleInfo = new HashMap<>();

    {
        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();
        set1.add("role1");
        set1.add("role2");
        set2.add("root");
        roleInfo.put("jack", set1);
        roleInfo.put("test", set2);
    }


    //权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        String name = (String) principalCollection.getPrimaryPrincipal();

        Set<String> permission = getPermissionFromDb(name);

        Set<String> roles = getRoleFromDB(name);

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roles);
        simpleAuthorizationInfo.setStringPermissions(permission);


        return simpleAuthorizationInfo;
    }

    private Set<String> getRoleFromDB(String name) {
        return roleInfo.get(name);
    }

    private Set<String> getPermissionFromDb(String name) {
        return permissionInfo.get(name);
    }

    //登录时候用
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //获取身份信息
        String name = (String) authenticationToken.getPrincipal();


        String passWord = getPassWord(name);

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name, passWord, this.getName());

        return simpleAuthenticationInfo;
    }

    private String getPassWord(String s) {
        return userInfo.get(s);
    }
}
