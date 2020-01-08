package com.testspringboot.demo.friends.entity;

/**
 * 好友
 */
public class UserFriend {
    private int id;
    private String userName;

    public UserFriend(int id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
