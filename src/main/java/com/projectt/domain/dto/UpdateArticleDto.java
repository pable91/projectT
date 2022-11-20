package com.projectt.domain.dto;

import lombok.Getter;

@Getter
public class UpdateArticleDto {
    private Long articleId;
    private String articleTitle;
    private String articleContents;

    @Override
    public String toString() {
        return "UpdateArticleDto{" +
                "articleId='" + articleId + '\'' +
                ", articleTitle='" + articleTitle + '\'' +
                ", articleContents='" + articleContents + '\'' +
                '}';
    }
}