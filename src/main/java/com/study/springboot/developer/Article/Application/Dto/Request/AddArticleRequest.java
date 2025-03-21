package com.study.springboot.developer.Article.Application.Dto.Request;

import com.study.springboot.developer.Article.Domain.Entity.Article;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddArticleRequest {
    private String title;
    private String content;
    private String author;

    public AddArticleRequest(Article article) {
        this.title = article.getTitle();
        this.content = article.getContent();
        this.author = article.getAuthor();
    }
}
