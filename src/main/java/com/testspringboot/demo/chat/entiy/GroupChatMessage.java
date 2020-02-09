package com.testspringboot.demo.chat.entiy;

import java.util.Date;

public class GroupChatMessage {
    private int sendUserId;
    private int receiveGroupId;
    public String content;
    private Date date = new Date();

    public GroupChatMessage() {
    }

    public GroupChatMessage(int sendUserId, int receiveGroupId, String content) {
        this.sendUserId = sendUserId;
        this.receiveGroupId = receiveGroupId;
        this.content = content;
    }

    public int getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(int sendUserId) {
        this.sendUserId = sendUserId;
    }

    public int getReceiveGroupId() {
        return receiveGroupId;
    }

    public void setReceiveGroupId(int receiveGroupId) {
        this.receiveGroupId = receiveGroupId;
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
