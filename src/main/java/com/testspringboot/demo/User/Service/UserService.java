package com.testspringboot.demo.User.Service;

import com.testspringboot.demo.User.Entity.Role;

import java.util.List;

public interface UserService {
    List<Role> findRoleListByUserId(int userId);
}
