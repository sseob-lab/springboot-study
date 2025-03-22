package com.study.springboot.developer.Article.Infra.Repositories;

import com.study.springboot.developer.Article.Domain.Entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleJpaRepository extends JpaRepository<Article, Long> {
}
