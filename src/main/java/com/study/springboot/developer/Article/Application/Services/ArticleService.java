package com.study.springboot.developer.Article.Application.Services;

import com.study.springboot.developer.Article.Application.Dto.Request.UpdateArticleRequest;
import com.study.springboot.developer.Article.Application.Dto.Request.AddArticleRequest;
import com.study.springboot.developer.Article.Domain.Entity.Article;
import com.study.springboot.developer.Article.Domain.Repositories.ArticleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public Article add(AddArticleRequest request) {
        return articleRepository.save(request.toEntity());
    }

    @Transactional
    public Article update(Long id, UpdateArticleRequest request) {
        Article article = articleRepository.findById(id).orElseThrow();

        article.update(
            request.getTitle(),
            request.getContent()
        );

        return article;
    }

    public void delete(Long id) {
        Article existingArticle = articleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 글입니다."));

        articleRepository.delete(existingArticle);
    }
}
