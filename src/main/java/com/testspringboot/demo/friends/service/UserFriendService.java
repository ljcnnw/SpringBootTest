package com.testspringboot.demo.friends.service;

import com.testspringboot.demo.config.ResultData;
import com.testspringboot.demo.friends.entity.UserFriend;
import com.testspringboot.demo.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserFriendService {
    @Autowired
    private RedisTemplate<String, UserFriend> redisTemplate;


    /**
     * 添加好友
     * @return
     */
    public void addFriend(int userId,int friendId){
        UserFriend userFriend = new UserFriend(friendId,"jjjj");
        RedisUtil.getInstance().sSet(RedisUtil.FriendsList+userId,userFriend);
    }

    public Set<UserFriend> getFriendsList(int userId){
        Set<UserFriend> userFriends = redisTemplate.opsForSet().members(String.valueOf(userId));
        return userFriends;
    }
}
