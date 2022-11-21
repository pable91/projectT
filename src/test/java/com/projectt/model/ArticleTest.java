package com.projectt.model;

import com.projectt.domain.dto.request.AddArticleDto;
import com.projectt.domain.dto.request.UpdateArticleDto;
import com.projectt.domain.model.Article;
import com.projectt.domain.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ArticleTest {

    @Test
    void createArticleTest() {
        User user = new User("user1", "1234");

        AddArticleDto addArticleDto = new AddArticleDto("title", "contents");
        Article article = Article.of(addArticleDto, user);

        Assertions.assertThat(article.getTitle()).isEqualTo("title");
        Assertions.assertThat(article.getContents()).isEqualTo("contents");
        Assertions.assertThat(article.getUser().getUserId()).isEqualTo("user1");
        Assertions.assertThat(article.getUser().getPw()).isEqualTo("1234");
    }

    @Test
    void updateArticleTest() {
        User user = new User("user1", "1234");

        AddArticleDto addArticleDto = new AddArticleDto("title", "contents");
        Article article = Article.of(addArticleDto, user);

        UpdateArticleDto updateArticleDto = new UpdateArticleDto(1L, "t","c");
        article.update(updateArticleDto);

        Assertions.assertThat(article.getTitle()).isEqualTo("t");
        Assertions.assertThat(article.getContents()).isEqualTo("c");
        Assertions.assertThat(article.getUser().getUserId()).isEqualTo("user1");
        Assertions.assertThat(article.getUser().getPw()).isEqualTo("1234");
    }
}
