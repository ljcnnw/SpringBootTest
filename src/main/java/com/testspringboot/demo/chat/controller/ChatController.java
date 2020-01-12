package com.testspringboot.demo.chat.controller;

import com.testspringboot.demo.chat.entiy.PrivateChatMessage;
import com.testspringboot.demo.chat.service.ChatService;
import com.testspringboot.demo.config.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("chat")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @MessageMapping("/singleChat/private")
    public ResultData singleChatForPrivate(PrivateChatMessage privateChatMessage){
        try {
            chatService.sendPrivateMessage(privateChatMessage);
            return new ResultData(200,"发送成功");
        }catch (Exception e){
            return new ResultData(500,"发送失败");
        }
    }

    @PostMapping("saveChatRecording")
    public ResultData saveChatRecording(@RequestBody PrivateChatMessage privateChatMessage){
        if(chatService.saveChatRecording(privateChatMessage)){
            return new ResultData(200,"保存成功");
        }else {
            return new ResultData(500,"保存失败");
        }

    }
}
