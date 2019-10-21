package com.testspringboot.demo.User.Mapper;


import com.testspringboot.demo.User.Entity.Permission;
import com.testspringboot.demo.User.Entity.Role;
import com.testspringboot.demo.User.Entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserDao {

    @Select("select sr.role_name as roleName,sr.role_code as roleCode,sr.id as id from user_role ur left join sys_role sr on sr.id = ur.role_id where ur.user_id = #{id}")
    @Results(
            value = {
                    @Result(id = true, property = "id", column = "id"),
                    @Result(property = "roleName", column = "roleName"),
                    @Result(property = "roleCode", column = "roleCode"),
                    @Result(property = "permissionList", column = "id",
                            many = @Many(select = "com.testspringboot.demo.User.Mapper.UserDao.findPermissionByRoleId", fetchType = FetchType.DEFAULT)
                    )
            }
    )
    List<Role> findRoleListUserById(@Param("id") int id);


    @Select("select sp.id as id ,sp.permission_code as permissionCode,sp.permission_name as permissionName from role_permission rp LEFT JOIN sys_permission sp on rp.permission_id = sp.id where rp.role_id = #{roleId}")
    List<Permission> findPermissionByRoleId(@Param("roleId") int roleId);

    @Select("select u.id as id,u.user_name as userName,u.pass_word as password from sys_user u where u.user_Name = #{userName}")
    User getUserByUserName(@Param("userName") String userName);

    @Select("select u.id as id,u.user_name as userName,u.pass_word as password from sys_user u where u.user_Name = #{userName}")
    @Results(value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "userName", column = "userName"),
            @Result(property = "password", column = "password"),
            @Result(property = "roleList", column = "id",
                    many = @Many(select = "com.testspringboot.demo.User.Mapper.UserDao.findRoleListUserById", fetchType = FetchType.DEFAULT)
            ),
    })
    User findAllUserInfoByUserName(@Param("userName") String userName);

    @Options(useGeneratedKeys = true, keyProperty = "user.id", keyColumn = "id")
    @Insert("insert into sys_user (user_name,pass_word) values (#{user.userName},#{user.password})")
    int insertUser(@Param("user") User user);

    @Select("select su.id as id,su.user_name as userName,ui.user_id_card as 'userInfo.userIdCard',ui.email as 'userInfo.email',ui.tel as 'userInfo.tel',ui.real_name as 'userInfo.realName',ui.create_date as 'userInfo.createDate' from sys_user su left join user_info ui on su.id = ui.user_id where su.id = #{id}")
    User getUserById(@Param("id") int id);
}
