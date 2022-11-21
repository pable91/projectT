package com.projectt.domain.dto.response;

import com.projectt.domain.model.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ArticleResponseDto {

    private Long articleId;

    public ArticleResponseDto() {};

    public ArticleResponseDto(Article article) {
        this.articleId = article.getId();
    }
}
