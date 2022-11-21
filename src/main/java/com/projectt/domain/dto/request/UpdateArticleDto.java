package com.projectt.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
public class UpdateArticleDto {

    @Min(1L)
    private Long articleId;
    @NotBlank
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
