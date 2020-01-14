package com.testspringboot.demo.chat.service;

import com.testspringboot.demo.User.Mapper.UserDao;
import com.testspringboot.demo.chat.entiy.GroupChat;
import com.testspringboot.demo.chat.Mapper.GroupChatDao;
import com.testspringboot.demo.util.SerializeUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 群聊
 * @author ljc
 */
@Service
public class GroupChatService {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private GroupChatDao groupChatDao;
    @Autowired
    private SqlSession sqlSession;

    public GroupChat newGroupChat(GroupChat groupChat) {
        List<Integer> friendList = new ArrayList<>();
        friendList.add(groupChat.getCreateUserId());
        groupChat.setGroupChatUserIdListForByte(SerializeUtil.serizlize(friendList));
        sqlSession.getMapper(GroupChatDao.class).insertNewGroupChat(groupChat);
        return groupChat;
    }

    public GroupChat getGroupChatById(int id){
        GroupChat groupChat = groupChatDao.getGroupChatById(id);
        groupChat.setGroupChatUserIdList((ArrayList<Integer>)SerializeUtil.deserialize(groupChat.getGroupChatUserIdListForByte()));
        return groupChat;
    }

    public int joinGroupChat(GroupChat groupChat,int userId){
        return 0;
    }
}
