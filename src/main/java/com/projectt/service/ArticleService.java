package com.projectt.service;

import com.projectt.domain.dto.request.AddArticleDto;
import com.projectt.domain.dto.request.UpdateArticleDto;
import com.projectt.domain.model.Article;

public interface ArticleService {
    Article addArticle(final AddArticleDto articleDto);
    Article updateArticle(final UpdateArticleDto articleDto);
    Article findById(final Long articleId);
    Article delete(final Long articleId);
}
