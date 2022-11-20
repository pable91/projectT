package com.projectt.service;

import com.projectt.domain.model.Article;
import com.projectt.domain.model.User;
import com.projectt.dto.AddArticleDto;
import com.projectt.repository.ActionRepository;
import com.projectt.util.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActionService {

    private final ActionRepository actionRepository;

    public Article addArticle(AddArticleDto articleDto) {
        Optional<User> currentUser = SecurityUtil.getCurrentUser();

        Article article = Article.of(articleDto, currentUser.get());

        actionRepository.save(article);

        return article;
    }
}
