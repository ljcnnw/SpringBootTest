package com.testspringboot.demo.chat.entiy;

import java.util.Date;

public class GroupChatMessage {
    private int SendUserId;
    private int ReceiveGroupId;
    public String content;
    private Date date = new Date();

    public GroupChatMessage() {
    }

    public GroupChatMessage(int sendUserId, int receiveGroupId, String content) {
        SendUserId = sendUserId;
        ReceiveGroupId = receiveGroupId;
        this.content = content;
    }

    public int getSendUserId() {
        return SendUserId;
    }

    public void setSendUserId(int sendUserId) {
        SendUserId = sendUserId;
    }

    public int getReceiveGroupId() {
        return ReceiveGroupId;
    }

    public void setReceiveGroupId(int receiveGroupId) {
        ReceiveGroupId = receiveGroupId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
