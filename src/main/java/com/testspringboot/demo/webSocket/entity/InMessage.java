package com.testspringboot.demo.webSocket.entity;

import java.util.Date;

/**
 * 接受消息
 */
public class InMessage {
    private String in;
    private String out;
    private Date date = new Date();
    private String content;

    public String getIn() {
        return in;
    }

    public void setIn(String in) {
        this.in = in;
    }

    public String getOut() {
        return out;
    }

    public void setOut(String out) {
        this.out = out;
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
