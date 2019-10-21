package com.testspringboot.demo.article.controller;

import com.testspringboot.demo.article.entity.ArticleInfo;
import com.testspringboot.demo.article.service.ArticleInfoService;
import com.testspringboot.demo.config.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.transform.Result;

/**
 * 文章接口
 */
@RestController
@RequestMapping("articleInfo")
@Api(description = "文章接口")
public class ArtilceInfoController {

    @Autowired
    private ArticleInfoService articleInfoService;

    @PostMapping("newArticle")
    @ApiOperation(value = "发布新帖", notes = "发布新帖接口")
    public ResultData newArticle(@RequestBody @Valid ArticleInfo articleInfo, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                return new ResultData(500, bindingResult.getFieldError().getDefaultMessage());
            }
            int id = articleInfoService.newArticle(articleInfo);
            return new ResultData(200, id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData(500, "发布失败");
        }
    }

    @GetMapping("getArticleById")
    @ApiOperation(value = "获取文章", notes = "获取文章接口")
    public ResultData getArticleById(@RequestParam int id) {
        try {
            ArticleInfo articleInfo = articleInfoService.getArticleInfoById(id);
            if (articleInfo != null) {
                return new ResultData(200, articleInfo);
            }else {
                return new ResultData(500, "文章不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData(500, "获取文章失败");
        }
    }
}
