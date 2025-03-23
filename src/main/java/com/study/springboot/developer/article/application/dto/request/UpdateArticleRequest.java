package com.study.springboot.developer.article.application.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateArticleRequest {
    private String title;
    private String content;
}
