package com.study.springboot.developer.article.application.services;

import com.study.springboot.developer.article.domain.entity.Article;
import com.study.springboot.developer.article.domain.repositories.ArticleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static java.time.LocalDateTime.now;

@SpringBootTest
class ArticleServiceTest {
    @MockBean
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleService articleService;

    @Test
    public void testFindAllArticles() {
        // Given mock데이터준비
        List<Article> articles = Arrays.asList(
                new Article(1L, "title1", "content1", "shim yosub", now(), now()),
                new Article(1L, "title1", "content1", "shim hyonsub", now(), now())
        );

        // When
        // findAll 호출 시 위에서 정의해둔 mock 데이터가 반환되도록 설정
        Mockito.when(articleRepository.findAll()).thenReturn(articles);

        List<Article> result = articleService.getAllArticles();

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("shim yosub", result.get(0).getAuthor());
        Assertions.assertEquals("shim hyonsub", result.get(1).getAuthor());
    }
}