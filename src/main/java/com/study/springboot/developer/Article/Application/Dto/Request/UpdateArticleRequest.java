package com.study.springboot.developer.Article.Application.Dto.Request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateArticleRequest {
    private String title;
    private String content;
}
