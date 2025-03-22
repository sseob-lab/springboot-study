package com.study.springboot.developer.Article.Infra.Repositories;

import com.study.springboot.developer.Article.Domain.Entity.Article;
import com.study.springboot.developer.Article.Domain.Repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ArticleRepositoryImpl implements ArticleRepository {
    @Autowired
    private ArticleJpaRepository articleJpaRepository;

    @Override
    public List<Article> findAll() {
        return articleJpaRepository.findAll();
    }

    @Override
    public Optional<Article> findById(Long id) {
        return articleJpaRepository.findById(id);
    }

    @Override
    public Article save(Article article) {
        return articleJpaRepository.save(article);
    }

    @Override
    public void delete(Article article) {
        articleJpaRepository.delete(article);
    }
}
