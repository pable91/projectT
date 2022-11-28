package com.projectt.domain.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
public class AddArticleDto {

    @ApiModelProperty(value = "글 제목", example = "title1", required = true)
    @NotBlank
    private final String articleTitle;

    @ApiModelProperty(value = "글 내용", example = "contents1", required = true)
    @NotBlank
    private final String articleContents;

    @Override
    public String toString() {
        return "AddArticleDto{" +
                "articleTitle='" + articleTitle + '\'' +
                ", articleContents='" + articleContents + '\'' +
                '}';
    }
}
