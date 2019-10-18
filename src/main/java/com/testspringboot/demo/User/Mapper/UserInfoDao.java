package com.testspringboot.demo.User.Mapper;

import com.testspringboot.demo.User.Entity.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface UserInfoDao {

    @Insert("insert into user_info(user_id,real_name,tel,email,user_id_card) values (#{userInfo.userId},#{userInfo.realName},#{userInfo.tel},#{userInfo.email},#{userInfo.userIdCard})")
    int insertUserInfo(@Param("userInfo") UserInfo userInfo);
}
