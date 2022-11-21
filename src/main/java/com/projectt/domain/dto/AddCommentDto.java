package com.projectt.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AddCommentDto {

    private Long articleId;
    private String commentsContents;
}
