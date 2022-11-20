package com.projectt.service;

import com.projectt.domain.dto.UpdateArticleDto;
import com.projectt.domain.model.Article;
import com.projectt.domain.model.User;
import com.projectt.domain.dto.AddArticleDto;
import com.projectt.repository.ArticleRepository;
import com.projectt.common.ErrorCode;
import com.projectt.common.exception.NotFoundArticleException;
import com.projectt.common.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Article addArticle(AddArticleDto articleDto) {
        Optional<User> currentUser = SecurityUtil.getCurrentUser();

        Article article = Article.of(articleDto, currentUser.get());

        articleRepository.save(article);

        return article;
    }

    public Article updateArticle(UpdateArticleDto articleDto) {
        Article article = articleRepository.findById(articleDto.getArticleId()).orElseThrow(
                () -> new NotFoundArticleException(ErrorCode.NOT_FOUND_ARTICLE)
        );

        article.update(articleDto);

        return article;
    }

    public Article findById(Long articleId) {
        return articleRepository.findById(articleId).orElseThrow(
                () -> new NotFoundArticleException(ErrorCode.NOT_FOUND_ARTICLE)
        );
    }

    public Article delete(Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(
                () -> new NotFoundArticleException(ErrorCode.NOT_FOUND_ARTICLE)
        );

        articleRepository.delete(article);

        return article;
    }
}
