package com.projectt.service;

import com.projectt.common.ErrorCode;
import com.projectt.common.exception.NotFoundArticleException;
import com.projectt.common.exception.NotFoundCommentException;
import com.projectt.domain.dto.request.AddCommentDto;
import com.projectt.domain.dto.response.CommentsResponseDto;
import com.projectt.domain.model.Article;
import com.projectt.domain.model.Comment;
import com.projectt.repository.ArticleRepository;
import com.projectt.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    public CommentsResponseDto deleteComment(Long commentsId) {
        Comment comment = commentRepository.findById(commentsId).orElseThrow(
                () -> new NotFoundCommentException(ErrorCode.NOT_FOUND_COMMENT)
        );

        commentRepository.delete(comment);

        Optional<Article> article = articleRepository.findById(comment.getArticle().getId());
        Long articleId = null;
        if (article.isPresent()) {
            articleId = article.get().getId();
        }

        return new CommentsResponseDto(articleId, commentsId);
    }
}
