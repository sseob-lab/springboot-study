package com.study.springboot.developer.article.userinterface.api.controller;

import com.study.springboot.developer.article.application.dto.request.UpdateArticleRequest;
import com.study.springboot.developer.article.application.dto.request.AddArticleRequest;
import com.study.springboot.developer.article.application.services.ArticleService;
import com.study.springboot.developer.article.domain.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class ArticleApiController {
    @Autowired
    ArticleService articleService;

    @PostMapping("/articles")
    public ResponseEntity<Article> add(@RequestBody AddArticleRequest request) {
        Article createdArticle = articleService.add(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdArticle);
    }

    @PutMapping("/articles/{id}")
    public ResponseEntity<Article> update(
            @PathVariable Long id,
            @RequestBody UpdateArticleRequest request
    ) {
        Article updatedArticle = articleService.update(id, request);

        return ResponseEntity.ok().body(updatedArticle);
    }

    @DeleteMapping("/articles/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        articleService.delete(id);

        return ResponseEntity.ok().build();
    }
}
