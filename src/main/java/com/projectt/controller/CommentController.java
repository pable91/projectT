package com.projectt.controller;

import com.projectt.common.ErrorCode;
import com.projectt.common.exception.NotFoundUserException;
import com.projectt.common.security.SecurityUtil;
import com.projectt.domain.dto.request.AddCommentDto;
import com.projectt.domain.dto.response.CommentsResponseDto;
import com.projectt.domain.model.User;
import com.projectt.service.ArticleServiceImpl;
import com.projectt.service.CommentService;
import com.projectt.service.UserServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final UserServiceImpl userServiceImpl;
    private final ArticleServiceImpl articleServiceImpl;

    @PostMapping("/comments")
    @ApiOperation(value = "댓글 등록", notes = "articleId는 1이상, contents는 필수값이다")
    public ResponseEntity<CommentsResponseDto> addComment(@RequestBody final AddCommentDto addCommentDTO) {
        CommentsResponseDto response = commentService.addComment(addCommentDTO);

        addPointByComment(response);

        log.info(response.toString());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/comments/{commentsId}")
    @ApiOperation(value = "댓글 삭제")
    public ResponseEntity<CommentsResponseDto> deleteComment(@ApiParam(value = "댓글 ID", example = "2") @PathVariable final Long commentsId) {

        CommentsResponseDto response = commentService.deleteComment(commentsId);

        deletePointByComments(response);

        return ResponseEntity.ok(response);
    }

    private void addPointByComment(final CommentsResponseDto response) {
        // 댓글 등록한 사람 add point
        userServiceImpl.increasePointByAddComments(
                SecurityUtil.getCurrentUser()
                        .orElseThrow(() -> new NotFoundUserException(ErrorCode.NOT_FOUND_USER)),
                2
        );

        // 원글 작성자 add Point
        User articleWriter = articleServiceImpl.findById(response.getArticleId()).getUser();
        userServiceImpl.increasePointByAddComments(
                articleWriter,
                1
        );
    }

    private void deletePointByComments(final CommentsResponseDto response) {
        // 댓글 등록한 사람 subtract point
        userServiceImpl.decreasePointByDeleteComments(
                SecurityUtil.getCurrentUser()
                        .orElseThrow(() -> new NotFoundUserException(ErrorCode.NOT_FOUND_USER)),
                2
        );

        // 원글 작성자 subtract Point
        User articleWriter = articleServiceImpl.findById(response.getArticleId()).getUser();
        userServiceImpl.decreasePointByDeleteComments(
                articleWriter,
                1
        );
    }
}
