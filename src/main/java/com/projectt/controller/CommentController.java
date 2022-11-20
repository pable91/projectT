package com.projectt.controller;

import com.projectt.common.ErrorCode;
import com.projectt.common.exception.NotFoundUserException;
import com.projectt.common.security.SecurityUtil;
import com.projectt.domain.dto.AddCommentDto;
import com.projectt.domain.dto.response.CommentsResponseDto;
import com.projectt.domain.model.Comment;
import com.projectt.domain.model.User;
import com.projectt.service.ArticleService;
import com.projectt.service.CommentService;
import com.projectt.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;
    private final ArticleService articleService;

    @PostMapping("/comments")
    public ResponseEntity<CommentsResponseDto> addComment(@RequestBody AddCommentDto addCommentDTO) {
        CommentsResponseDto response = commentService.addComment(addCommentDTO);

        addPointByComment(response);

        log.info(response.toString());
        return ResponseEntity.ok(response);
    }

    private void addPointByComment(CommentsResponseDto response) {
        // 댓글 등록한 사람 add point
        userService.increasePointByAddComments(
                SecurityUtil.getCurrentUser()
                        .orElseThrow(() -> new NotFoundUserException(ErrorCode.NOT_FOUND_USER)),
                2
        );

        // 원글 작성자 add Point
        User articleWriter = articleService.findById(response.getArticleId()).getUser();
        userService.increasePointByAddComments(
                articleWriter,
                1
        );
    }
}
