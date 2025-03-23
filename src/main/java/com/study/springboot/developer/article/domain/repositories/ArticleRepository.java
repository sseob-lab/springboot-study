package com.study.springboot.developer.article.domain.repositories;

import com.study.springboot.developer.article.domain.entity.Article;
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
