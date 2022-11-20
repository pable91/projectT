package com.projectt.domain.dto;

import lombok.Getter;

@Getter
public class AddArticleDto {
    private String articleTitle;
    private String articleContents;

    @Override
    public String toString() {
        return "AddArticleDto{" +
                "articleTitle='" + articleTitle + '\'' +
                ", articleContents='" + articleContents + '\'' +
                '}';
    }
}
