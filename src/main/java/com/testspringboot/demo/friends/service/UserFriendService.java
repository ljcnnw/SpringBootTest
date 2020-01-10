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
        RedisUtil.getInstance().sSet(RedisUtil.FriendsList + userId, userFriend);
    }

    public Set<Object> getFriendsList(int userId) {
//        byte[] ul = RedisUtil.getInstance().sGetForByte(RedisUtil.FriendsList + userId);
        System.out.println(RedisUtil.FriendsList + userId);
        Set<Object> userFriends2 = RedisUtil.getInstance().sGet(RedisUtil.FriendsList + userId);
        //Set<Object> userList = RedisUtil.getInstance().sGet(RedisUtil.FriendsList + userId);
  //      Set<UserFriend> userFriends = (Set<UserFriend>) SerializeUtil.deserialize(ul);
        return userFriends2;
    }
}
