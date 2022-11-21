package com.projectt.domain.dto.response;

import lombok.Getter;

@Getter
public class CommentsResponseDto {
    private Long articleId;
    private Long commentsId;

    public CommentsResponseDto(Long articleId, Long commentsId) {
        this.articleId = articleId;
        this.commentsId = commentsId;
    }

    @Override
    public String toString() {
        return "CommentsResponseDto{" +
                "articleId=" + articleId +
                ", commentsId=" + commentsId +
                '}';
    }
}
