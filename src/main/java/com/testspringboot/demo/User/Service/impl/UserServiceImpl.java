package com.testspringboot.demo.User.Service.impl;

import com.testspringboot.demo.User.Entity.Role;
import com.testspringboot.demo.User.Mapper.UserDao;
import com.testspringboot.demo.User.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<Role> findRoleListByUserId(int userId) {
        return userDao.findRoleListUserById(userId);
    }
}
