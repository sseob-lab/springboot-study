package com.study.springboot.developer.article.infra.repositories;

import com.study.springboot.developer.article.domain.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleJpaRepository extends JpaRepository<Article, Long> {
}
