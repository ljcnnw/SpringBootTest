package com.testspringboot.demo.chat.Mapper;

import com.testspringboot.demo.User.Entity.User;
import com.testspringboot.demo.chat.entiy.GroupChat;
import com.testspringboot.demo.chat.entiy.GroupChatUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface GroupChatDao {
    @Options(useGeneratedKeys = true, keyProperty = "groupChat.id", keyColumn = "id")
    @Insert("insert into group_chat (group_chat_user_id_list_for_byte,boss_user_id,group_chat_name,create_user_id) values (#{groupChat.groupChatUserIdListForByte},#{groupChat.bossUserId},#{groupChat.groupChatName},#{groupChat.createUserId})")
    int insertNewGroupChat(@Param("groupChat") GroupChat groupChat);

    @Select("SELECT g.id as id , g.boss_user_id as bossUserId,g.group_chat_name as groupChatName,g.create_user_id as createUserId from group_chat g where g.id = #{id}")
    @Results(
            value = {
                    @Result(id = true, property = "id", column = "id"),
                    @Result(property = "bossUserId", column = "bossUserId"),
                    @Result(property = "groupChatName", column = "groupChatName"),
                    @Result(property = "createUserId", column = "createUserId"),
                    @Result(property = "groupChatUserList", column = "id",
                            many = @Many(select = "com.testspringboot.demo.chat.Mapper.GroupChatDao.getGroupChatUserListByGroupChatId", fetchType = FetchType.DEFAULT)
                    )
            }
    )
    //@Select("SELECT g.group_chat_user_id_list_for_byte as groupChatUserIdListForByte , g.boss_user_id as bossUserId,g.group_chat_name as groupChatName,g.create_user_id as createUserId from group_chat g where g.id = #{id}")
    GroupChat getGroupChatById(@Param("id") int id);

    @Select("select g.id as id,g.group_chat_id as groupChatId,g.user_id as userId,g.nick_name as nickName,g.join_date as joinDate,g.is_boss as isBoss,g.avatar_img as avatarImg from group_chat_user g where g.group_chat_id = #{groupChatId}")
    List<GroupChatUser> getGroupChatUserListByGroupChatId(@Param("groupChatId") int groupChatId);


    //@Options(useGeneratedKeys = true, keyProperty = "groupChat.id", keyColumn = "id")
    @Insert("insert into group_chat_user(group_chat_id,user_id,nick_name,is_boss,avatar_img) values (#{groupChatUser.groupChatId},#{groupChatUser.userId},#{groupChatUser.nickName},#{groupChatUser.isBoss},#{groupChatUser.avatarImg})")
    int joinGrouChat(@Param("groupChatUser") GroupChatUser groupChatUser);


}
