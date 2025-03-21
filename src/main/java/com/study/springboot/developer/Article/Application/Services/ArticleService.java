package com.study.springboot.developer.Article.Application.Services;

import com.study.springboot.developer.Article.Application.Dto.Request.UpdateArticleRequest;
import com.study.springboot.developer.Article.Domain.Entity.Article;
import com.study.springboot.developer.Article.Domain.Repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    @Autowired
    ArticleRepository articleRepository;

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public Optional<Article> findById(Long id) {
        return articleRepository.findById(id);
    }

    public Article update(Long id, UpdateArticleRequest request) {
        Article existingArticle = articleRepository.findById(id).orElseThrow();

        existingArticle.setTitle(request.getTitle());
        existingArticle.setContent(request.getContent());

        return articleRepository.save(existingArticle);
    }
}
