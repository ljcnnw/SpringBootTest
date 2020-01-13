package com.testspringboot.demo.chat.service;

import com.testspringboot.demo.chat.entiy.PrivateChatMessage;
import com.testspringboot.demo.chat.entiy.PrivateChatRecording;
import com.testspringboot.demo.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PrivateChatService {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    /**
     * 发送私人消息
     *
     * @param privateChatMessage
     */
    public void sendPrivateMessage(PrivateChatMessage privateChatMessage) {
        //保存记录到redis
        boolean flag = RedisUtil.getInstance().lSet(RedisUtil.PRIVATE_CHAT_RECORDING + privateChatMessage.getSendUserId() + ":" + privateChatMessage.getReceiveUserId(),
                new PrivateChatRecording(true, privateChatMessage.getContent(), new Date()));
        if (flag) {
            simpMessagingTemplate.convertAndSend("/chat/single/" + privateChatMessage.getReceiveUserId(), new PrivateChatMessage(privateChatMessage.getSendUserId(), privateChatMessage.getReceiveUserId(), privateChatMessage.getContent()));
        }
    }

    /**
     * 保存接收到的消息到Redis
     *
     * @return
     */
    public boolean saveChatRecording(PrivateChatMessage privateChatMessage) {
        try {
            boolean flag = RedisUtil.getInstance().lSet(RedisUtil.PRIVATE_CHAT_RECORDING + privateChatMessage.getSendUserId() + ":" + privateChatMessage.getReceiveUserId(),
                    new PrivateChatRecording(false, privateChatMessage.getContent(), new Date()));
            return flag;
        } catch (Exception e) {
            return false;
        }

    }
}
