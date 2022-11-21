package com.projectt.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
public class AddArticleDto {

    @NotBlank
    private String articleTitle;

    @NotBlank
    private String articleContents;

    @Override
    public String toString() {
        return "AddArticleDto{" +
                "articleTitle='" + articleTitle + '\'' +
                ", articleContents='" + articleContents + '\'' +
                '}';
    }
}
