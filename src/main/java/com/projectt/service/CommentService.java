package com.projectt.service;

import com.projectt.domain.dto.request.AddCommentDto;
import com.projectt.domain.dto.response.CommentsResponseDto;

public interface CommentService {
    CommentsResponseDto addComment(final AddCommentDto addCommentDTO);
    CommentsResponseDto deleteComment(final Long commentsId);
}
