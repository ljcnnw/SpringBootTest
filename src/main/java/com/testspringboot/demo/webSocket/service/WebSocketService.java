package com.testspringboot.demo.webSocket.service;

import com.testspringboot.demo.webSocket.entity.InMessage;
import com.testspringboot.demo.webSocket.entity.OutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public void sendPrivateMessage(InMessage inMessage) {
        simpMessagingTemplate.convertAndSend("/chat/single/"+inMessage.getOut(), new OutMessage(inMessage.getContent()));
    }
}
