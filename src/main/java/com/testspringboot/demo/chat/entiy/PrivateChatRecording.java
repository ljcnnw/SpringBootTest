package com.testspringboot.demo.chat.entiy;

import java.util.Date;

/**
 * 聊天记录(私人)
 */
public class PrivateChatRecording {
    private boolean isMine;//true 代表是本人发出的消息
    private String content;
    private Date date;

    public PrivateChatRecording() {
    }

    public PrivateChatRecording(boolean isMine, String content, Date date) {
        this.isMine = isMine;
        this.content = content;
        this.date = date;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
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
