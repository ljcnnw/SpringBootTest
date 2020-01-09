package com.testspringboot.demo.friends.service;

import com.testspringboot.demo.config.ResultData;
import com.testspringboot.demo.friends.entity.UserFriend;
import com.testspringboot.demo.util.RedisUtil;
import com.testspringboot.demo.util.SerializeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserFriendService {


    /**
     * 添加好友
     *
     * @return
     */
    public void addFriend(int userId, int friendId) {
        UserFriend userFriend = new UserFriend(friendId, "jjjj");
        byte[] uf = SerializeUtil.serizlize(userFriend);
        RedisUtil.getInstance().sSet(RedisUtil.FriendsList + userId, uf);
    }

    public Set<UserFriend> getFriendsList(int userId) {
        Set<Object> userList = RedisUtil.getInstance().sGet(RedisUtil.FriendsList + userId);
        Set<UserFriend> userFriends = new HashSet<>();
        for (Object o : userList) {
            userFriends.add((UserFriend)SerializeUtil.deserialize(o.toString().getBytes()));
        }
        return userFriends;
    }
}
