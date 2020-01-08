package com.testspringboot.demo.friends.controller;

import com.testspringboot.demo.config.ResultData;
import com.testspringboot.demo.friends.entity.UserFriend;
import com.testspringboot.demo.friends.service.UserFriendService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("userFriend")
public class UserFriendController {
    @Autowired
    private UserFriendService userFriendService;


    @PostMapping("addFriend")
    public ResultData addFriend(@Param("userId") int userId,@Param("friendId") int friendId) {
        try {
            userFriendService.addFriend(userId, friendId);
            return new ResultData(200, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData(500, "添加失败");
        }
    }
    @PostMapping("getFriendsList")
    public ResultData getFriendsList(@Param("userId") int userId) {
        try {
            Set<UserFriend> userFriends = userFriendService.getFriendsList(userId);
            return new ResultData(200, userFriends);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData(500, "添加失败");
        }
    }
}
