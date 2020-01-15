package com.testspringboot.demo.chat.entiy;

import io.swagger.models.auth.In;

import java.util.Date;
import java.util.List;

/**
 * 群聊
 */
public class GroupChat {
    private int id;
    private List<Integer> groupChatUserIdList;//群聊成员ID
    private byte[] groupChatUserIdListForByte;
    private Date createDate;//创建时间
    private String bossUserId;//群主id
    private String groupChatName;//群聊名称
    private int createUserId;//创建人id
    private List<GroupChatUser> groupChatUserList;

    public GroupChat() {
    }

    public List<GroupChatUser> getGroupChatUserList() {
        return groupChatUserList;
    }

    public void setGroupChatUserList(List<GroupChatUser> groupChatUserList) {
        this.groupChatUserList = groupChatUserList;
    }

    public byte[] getGroupChatUserIdListForByte() {
        return groupChatUserIdListForByte;
    }

    public void setGroupChatUserIdListForByte(byte[] groupChatUserIdListForByte) {
        this.groupChatUserIdListForByte = groupChatUserIdListForByte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getGroupChatUserIdList() {
        return groupChatUserIdList;
    }

    public void setGroupChatUserIdList(List<Integer> groupChatUserIdList) {
        this.groupChatUserIdList = groupChatUserIdList;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getBossUserId() {
        return bossUserId;
    }

    public void setBossUserId(String bossUserId) {
        this.bossUserId = bossUserId;
    }

    public String getGroupChatName() {
        return groupChatName;
    }

    public void setGroupChatName(String groupChatName) {
        this.groupChatName = groupChatName;
    }

    public int getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(int createUserId) {
        this.createUserId = createUserId;
    }
}
