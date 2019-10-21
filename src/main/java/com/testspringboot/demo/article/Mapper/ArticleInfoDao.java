package com.testspringboot.demo.article.Mapper;

import com.testspringboot.demo.article.entity.ArticleInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ArticleInfoDao {

    @Options(useGeneratedKeys = true, keyProperty = "articleInfo.id", keyColumn = "id")
    @Insert("insert into article_info (user_id,content,cover_pic,title,user_name) values (#{articleInfo.userId},#{articleInfo.content},#{articleInfo.coverPic},#{articleInfo.title},#{articleInfo.userName})")
    int newArticle(@Param("articleInfo") ArticleInfo articleInfo);

    @Select("select ai.id as id,ai.title as title,ai.content as content,ai.cover_pic as coverPic,ai.user_id as userId,ai.create_date as createDate,ai.user_name as userName from article_info ai left join user_info ui on ui.user_id = ai.user_id where ai.id = #{id} and ai.del_flag = 0;")
    ArticleInfo getArticleInfoById(@Param("id") int id);
}
