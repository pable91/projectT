package com.projectt.service;

import com.projectt.common.exception.NotFoundArticleException;
import com.projectt.common.exception.NotFoundCommentException;
import com.projectt.domain.dto.request.AddCommentDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Test
    @DisplayName("등록되지않은 글에다가 댓글을 추가하면 예외를 던진다")
    void commentExceptionTest() {
        AddCommentDto addCommentDto = new AddCommentDto(1235L, "commentContents");
        Assertions.assertThrows(NotFoundArticleException.class, () -> {
            commentService.addComment(addCommentDto);
        });
    }

    @Test
    @DisplayName("존재하지않는 댓글을 삭제하려하면 예외를 던진다")
    void commentExceptionTest2() {
        Assertions.assertThrows(NotFoundCommentException.class, () -> {
            commentService.deleteComment(5L);
        });
    }
}
