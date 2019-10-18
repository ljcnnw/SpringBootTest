package com.testspringboot.demo.User.Service.impl;

import com.testspringboot.demo.User.Entity.Role;
import com.testspringboot.demo.User.Entity.User;
import com.testspringboot.demo.User.Mapper.UserDao;
import com.testspringboot.demo.User.Mapper.UserInfoDao;
import com.testspringboot.demo.User.Mapper.UserRoleDao;
import com.testspringboot.demo.User.Service.UserService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private SqlSession sqlSession;
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public List<Role> findRoleListByUserId(int userId) {
        return userDao.findRoleListUserById(userId);
    }

    @Override
    public int insertUser(User user) {
        sqlSession.getMapper(UserDao.class).insertUser(user);
        user.getUserInfo().setUserId(user.getId());
        userInfoDao.insertUserInfo(user.getUserInfo());
        userRoleDao.insertUserRole(user.getId(),3);
        return user.getId();
    }
}
