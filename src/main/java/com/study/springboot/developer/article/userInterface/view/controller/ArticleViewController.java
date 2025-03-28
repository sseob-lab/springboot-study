package com.study.springboot.developer.article.userinterface.view.controller;

import com.study.springboot.developer.article.application.dto.response.ArticleListViewResponse;
import com.study.springboot.developer.article.application.dto.response.ArticleViewResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.study.springboot.developer.article.application.services.ArticleService;
import com.study.springboot.developer.article.domain.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
public class ArticleViewController {
    @Autowired
    ArticleService articleService;

    @GetMapping("/articles")
    public String articleList(Model model) throws JsonProcessingException {
        List<ArticleListViewResponse> articles = articleService.getAllArticles()
            .stream()
            .map(ArticleListViewResponse::new)
            .toList();

        model.addAttribute("articles", articles);

        return "articleList";
    }

    @GetMapping("/articles/{id}")
    public String article(Model model, @PathVariable Long id) {
        ArticleViewResponse article = articleService.findById(id)
            .map(ArticleViewResponse::new)
            .orElseThrow();

        model.addAttribute("article", article);

        return "article";
    }

    @GetMapping("/new-article")
    public String newArticle(Model model, @RequestParam(required = false) Long id) {
        ArticleViewResponse article = (id == null)
            ? new ArticleViewResponse(Article.builder().build())
            : articleService.findById(id)
                .map(ArticleViewResponse::new)
                .orElseThrow();

        model.addAttribute("article", article);

        return "newArticle";
    }
}
