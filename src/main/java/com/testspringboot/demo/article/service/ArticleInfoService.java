package com.testspringboot.demo.article.service;

import com.testspringboot.demo.article.entity.ArticleInfo;
import org.apache.ibatis.annotations.Param;

public interface ArticleInfoService {
    int newArticle(ArticleInfo articleInfo);

    ArticleInfo getArticleInfoById(int id);
}
