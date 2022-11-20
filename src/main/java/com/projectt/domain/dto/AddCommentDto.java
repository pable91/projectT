package com.projectt.domain.dto;

import lombok.Getter;

@Getter
public class AddCommentDto {

    private Long articleId;
    private String commentsContents;
}
