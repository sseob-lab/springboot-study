package com.study.springboot.developer.Article.UserInterface.Controller;

import com.study.springboot.developer.Article.Application.Services.ArticleService;
import com.study.springboot.developer.Article.Domain.Entity.Article;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static java.time.LocalDateTime.now;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArticleController.class)
@AutoConfigureMockMvc
class ArticleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArticleService articleService; // 실제 서비스 대신 모킹된 객체를 사용

    @Test
    public void testFindAllArticles() throws Exception {
        // Given
        List<Article> articles = Arrays.asList(
                new Article(1L, "title1", "content1", "Yosub", now(), now()),
                new Article(1L, "title1", "content1", "Hyonsub", now(), now())
        );
        Mockito.when(articleService.getAllArticles()).thenReturn(articles);

        // When & Then
        mockMvc.perform(get("/test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].author").value("Yosub"))
                .andExpect(jsonPath("$[1].author").value("Hyonsub"));
    }
}