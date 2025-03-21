package com.study.springboot.developer.Article.UserInterface.Controller;

import com.study.springboot.developer.Article.Application.Dto.Response.ArticleListViewResponse;
import com.study.springboot.developer.Article.Application.Services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @GetMapping("/articles")
    public String articleList() {
        List<ArticleListViewResponse> articles = articleService.getAllArticles()
            .stream()
            .map(ArticleListViewResponse::new)
            .toList();

        return "articleList";
    }
}
