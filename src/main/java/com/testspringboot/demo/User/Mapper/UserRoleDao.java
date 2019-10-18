package com.testspringboot.demo.User.Mapper;

import net.bytebuddy.implementation.bind.annotation.Default;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface UserRoleDao {
    @Insert("insert into user_role (user_id,role_id) values (#{userId},#{roleId})")
    void insertUserRole(@Param("userId") int userId, @Param("roleId") int roleId);
}
