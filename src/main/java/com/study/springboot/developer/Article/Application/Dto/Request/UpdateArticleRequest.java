package com.study.springboot.developer.Article.Application.Dto.Request;

import com.study.springboot.developer.Article.Domain.Entity.Article;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateArticleRequest {
    private String title;
    private String content;

    public UpdateArticleRequest(Article article) {
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
