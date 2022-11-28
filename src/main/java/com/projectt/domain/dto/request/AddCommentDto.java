package com.projectt.domain.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
public class AddCommentDto {

    @ApiModelProperty(value = "글 ID", example = "1", required = true)
    @Min(1L)
    private final Long articleId;

    @ApiModelProperty(value = "댓글 내용", example = "comment contents555", required = true)
    @NotBlank
    private final String commentsContents;
}
