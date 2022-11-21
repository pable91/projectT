package com.projectt.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
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
