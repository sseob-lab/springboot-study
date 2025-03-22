package com.study.springboot.developer.Article.Domain.Repositories;

import com.study.springboot.developer.Article.Domain.Entity.Article;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository {
    List<Article> findAll();

    Optional<Article> findById(Long id);

    Article save(Article article);

    void delete(Article article);
}
