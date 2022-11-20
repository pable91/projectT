package com.projectt.service;

import com.projectt.common.ErrorCode;
import com.projectt.common.exception.NotFoundArticleException;
import com.projectt.domain.dto.AddCommentDto;
import com.projectt.domain.dto.response.CommentsResponseDto;
import com.projectt.domain.model.Article;
import com.projectt.domain.model.Comment;
import com.projectt.repository.ArticleRepository;
import com.projectt.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    public CommentsResponseDto addComment(AddCommentDto addCommentDTO) {
        Long articleId = addCommentDTO.getArticleId();
        Article article = articleRepository.findById(articleId).orElseThrow(
                () -> new NotFoundArticleException(ErrorCode.NOT_FOUND_ARTICLE)
        );

        Comment comment = Comment.from(addCommentDTO);
        comment.setArticle(article);

        commentRepository.save(comment);

        return new CommentsResponseDto(articleId, comment.getId());
    }
}
