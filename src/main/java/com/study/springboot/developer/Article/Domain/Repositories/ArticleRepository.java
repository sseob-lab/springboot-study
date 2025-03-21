package com.study.springboot.developer.Article.Domain.Repositories;

import com.study.springboot.developer.Article.Domain.Entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
