package com.testspringboot.demo.User.Service;

import com.testspringboot.demo.User.Entity.Role;
import com.testspringboot.demo.User.Entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    List<Role> findRoleListByUserId(int userId);

    int insertUser(User user);

    User getUserById(int id);
}
