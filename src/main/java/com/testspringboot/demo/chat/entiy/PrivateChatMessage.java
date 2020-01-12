package com.testspringboot.demo.chat.entiy;

import java.util.Date;

/**
 * 私聊信息
 */
public class PrivateChatMessage {
    private int SendUserId;
    private int ReceiveUserId;
    private Date date = new Date();
    private String content;

    public PrivateChatMessage() {
    }

    public PrivateChatMessage(int sendUserId, int receiveUserId, String content) {
        SendUserId = sendUserId;
        ReceiveUserId = receiveUserId;
        this.content = content;
    }

    public int getSendUserId() {
        return SendUserId;
    }

    public void setSendUserId(int sendUserId) {
        SendUserId = sendUserId;
    }

    public int getReceiveUserId() {
        return ReceiveUserId;
    }

    public void setReceiveUserId(int receiveUserId) {
        ReceiveUserId = receiveUserId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
