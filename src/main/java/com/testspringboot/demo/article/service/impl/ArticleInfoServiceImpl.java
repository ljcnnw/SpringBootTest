package com.testspringboot.demo.article.service.impl;

import com.testspringboot.demo.article.entity.ArticleInfo;
import com.testspringboot.demo.article.Mapper.ArticleInfoDao;
import com.testspringboot.demo.article.service.ArticleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class ArticleInfoServiceImpl implements ArticleInfoService {
    @Autowired
    private ArticleInfoDao articleInfoDao;

    @Override
    public int newArticle(ArticleInfo articleInfo) {
        articleInfoDao.newArticle(articleInfo);
        return articleInfo.getId();
    }

    @Override
    public ArticleInfo getArticleInfoById(int id) {
        return articleInfoDao.getArticleInfoById(id);
    }
}
