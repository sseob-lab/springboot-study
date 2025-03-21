package com.study.springboot.developer.Article.UserInterface.Controller;

import com.study.springboot.developer.Article.Application.Services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArticleController {
    @Autowired
    ArticleService testService;

    @GetMapping("/articles")
    public String articleList() {
        return "articleList";
    }
}
