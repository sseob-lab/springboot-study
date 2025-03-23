package com.study.springboot.developer.article.application.dto.request;

import com.study.springboot.developer.article.domain.entity.Article;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddArticleRequest {
    private String title;
    private String content;
    private String author = "shim"; // TODO: 로그인구현후 수정

    public Article toEntity() {
        return Article.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
