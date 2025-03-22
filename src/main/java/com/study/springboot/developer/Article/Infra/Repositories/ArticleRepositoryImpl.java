package com.study.springboot.developer.Article.Infra.Repositories;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.springboot.developer.Article.Domain.Entity.Article;
import com.study.springboot.developer.Article.Domain.Entity.QArticle;
import com.study.springboot.developer.Article.Domain.Repositories.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class ArticleRepositoryImpl implements ArticleRepository {
    private final ArticleJpaRepository articleJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Article> findAll() {
        return articleJpaRepository.findAll();
    }

    @Override
    public Optional<Article> findById(Long id) {
        Article article = jpaQueryFactory.selectFrom(QArticle.article)
            .where(QArticle.article.id.eq(id))
            .fetchFirst();

        return Optional.ofNullable(article);
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
