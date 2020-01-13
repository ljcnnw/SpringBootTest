package com.testspringboot.demo.chat.Mapper;

import com.testspringboot.demo.User.Entity.User;
import com.testspringboot.demo.chat.entiy.GroupChat;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface GroupChatDao {
    @Options(useGeneratedKeys = true, keyProperty = "groupChat.id", keyColumn = "id")
    @Insert("insert into group_chat (group_chat_user_id_list_for_byte,boss_user_id,group_chat_name,create_user_id) values (#{groupChat.groupChatUserIdListForByte},#{groupChat.bossUserId},#{groupChat.groupChatName},#{groupChat.createUserId})")
    int insertNewGroupChat(@Param("groupChat") GroupChat groupChat);

    @Select("SELECT g.group_chat_user_id_list_for_byte as groupChatUserIdListForByte , g.boss_user_id as bossUserId,g.group_chat_name as groupChatName,g.create_user_id as createUserId from group_chat g where g.id = #{id}")
    GroupChat getGroupChatById(@Param("id") int id);
}
