package com.testspringboot.demo.config;

import java.util.Date;

public class ResultData {
    public int code;
    public Object data;
    public Date time;

    public ResultData(int code, Object data) {
        this.code = code;
        this.data = data;
        this.time = new Date();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
