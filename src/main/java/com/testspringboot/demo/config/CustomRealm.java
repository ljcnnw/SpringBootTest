package com.testspringboot.demo.config;

import com.testspringboot.demo.User.Entity.Permission;
import com.testspringboot.demo.User.Entity.Role;
import com.testspringboot.demo.User.Entity.User;
import com.testspringboot.demo.User.Mapper.UserDao;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserDao userDao;

    /**
     * 权限校验
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("===============授权================");
        String userName = (String) principalCollection.getPrimaryPrincipal();
        User user = userDao.findAllUserInfoByUserName(userName);

        List<String> roleList = new ArrayList<>();
        List<String> permissionList = new ArrayList<>();

        

        List<Role> roles = user.getRoleList();
        for (Role r : roles) {
            roleList.add(r.getRoleCode());
            List<Permission> permissions = r.getPermissionList();
            for (Permission p : permissions) {
                permissionList.add(p.getPermissionName());
            }
        }

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(roleList);
        simpleAuthorizationInfo.addStringPermissions(permissionList);

        return simpleAuthorizationInfo;
    }

    /**
     * 登录
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("================认证==================");

        //获取用户名
        String username = (String) authenticationToken.getPrincipal();

        User user = userDao.getUserByUserName(username);

        String pwd = user.getPassword();

        return new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getName());
    }
}
