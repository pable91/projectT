package com.projectt.service;

import com.projectt.domain.dto.UpdateArticleDto;
import com.projectt.domain.model.Article;
import com.projectt.domain.model.User;
import com.projectt.domain.dto.AddArticleDto;
import com.projectt.repository.ActionRepository;
import com.projectt.util.ErrorCode;
import com.projectt.util.exception.NotFoundArticleException;
import com.projectt.util.exception.NotFoundUserException;
import com.projectt.util.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ActionService {

    private final ActionRepository actionRepository;

    public Article addArticle(AddArticleDto articleDto) {
        Optional<User> currentUser = SecurityUtil.getCurrentUser();

        Article article = Article.of(articleDto, currentUser.get());

        actionRepository.save(article);

        return article;
    }

    public Article updateArticle(UpdateArticleDto articleDto) {
        Article article = actionRepository.findById(articleDto.getArticleId()).orElseThrow(
                () -> new NotFoundArticleException(ErrorCode.NOT_FOUND_ARTICLE)
        );

        article.update(articleDto);

        return article;
    }
}
