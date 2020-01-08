package com.testspringboot.demo.webSocket.controller;

import com.testspringboot.demo.webSocket.entity.InMessage;
import com.testspringboot.demo.webSocket.entity.OutMessage;
import com.testspringboot.demo.webSocket.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketController {
    @Autowired
    private WebSocketService webSocketService;

    @MessageMapping("/v1/chat")
    @SendTo("/topic/game_chat")
    private OutMessage gameInfo(InMessage inMessage) {
        return new OutMessage(inMessage.getContent());
    }

    @MessageMapping("/singleChat/private")
    private void singleChatForPrivate(InMessage inMessage){
        webSocketService.sendPrivateMessage(inMessage);
    }

}
