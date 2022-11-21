package com.projectt.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
public class AddCommentDto {

    @Min(1L)
    private Long articleId;

    @NotBlank
    private String commentsContents;
}
