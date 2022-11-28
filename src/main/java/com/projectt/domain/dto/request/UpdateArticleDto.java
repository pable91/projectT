package com.projectt.domain.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
public class UpdateArticleDto {

    @ApiModelProperty(value = "글 ID", example = "1", required = true)
    @Min(1L)
    private final Long articleId;

    @ApiModelProperty(value = "글 제목", example = "title1 update", required = true)
    @NotBlank
    private final String articleTitle;

    @ApiModelProperty(value = "글 내용", example = "contents1 update")
    private final String articleContents;

    @Override
    public String toString() {
        return "UpdateArticleDto{" +
                "articleId='" + articleId + '\'' +
                ", articleTitle='" + articleTitle + '\'' +
                ", articleContents='" + articleContents + '\'' +
                '}';
    }
}
