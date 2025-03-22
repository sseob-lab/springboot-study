package com.study.springboot.developer.Article.Application.Dto.Request;

import com.study.springboot.developer.Article.Domain.Entity.Article;
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
