package com.testspringboot.demo.chat.controller;

import com.testspringboot.demo.chat.entiy.GroupChat;
import com.testspringboot.demo.chat.entiy.GroupChatUser;
import com.testspringboot.demo.chat.service.GroupChatService;
import com.testspringboot.demo.config.ResultData;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("groupChat")
public class GroupChatController {
    @Autowired
    private GroupChatService groupChatService;

    /**
     * 新建群聊
     *
     * @param groupChat
     * @return
     */
    @PostMapping("newGroupChat")
    public ResultData newGroupChat(@RequestBody GroupChat groupChat) {
        try {
            groupChat = groupChatService.newGroupChat(groupChat);
            return new ResultData(200, groupChat);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData(500, "建立群聊失败");
        }
    }

    /**
     * 通过群聊id获取群聊信息
     *
     * @param id
     * @return
     */
    @GetMapping("getGroupChatById")
    public ResultData getGroupChatById(@Param("id") int id) {
        try {
            GroupChat groupChat = groupChatService.getGroupChatById(id);
            if (groupChat == null) {
                return new ResultData(500, "查询群聊信息失败");
            }
            return new ResultData(200, groupChat);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData(500, "查询群聊信息失败");
        }
    }

    /**
     * 加入群聊
     * @param groupChatUser
     * @return
     */
    @PostMapping("joinGroupChat")
    public ResultData joinGroupChat(@RequestBody GroupChatUser groupChatUser) {
        try {
            groupChatService.joinGroupChat(groupChatUser);
            return new ResultData(200,"加入成功");
        }catch (Exception e){
            e.printStackTrace();
            return new ResultData(500,"加入失败");
        }
    }
}
