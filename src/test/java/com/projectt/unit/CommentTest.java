package com.projectt.unit;

import com.projectt.domain.dto.request.AddArticleDto;
import com.projectt.domain.dto.request.AddCommentDto;
import com.projectt.domain.model.Article;
import com.projectt.domain.model.Comment;
import com.projectt.domain.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CommentTest {

    @Test
    void createCommentTest() {
        AddCommentDto addCommentDto = new AddCommentDto(1L, "comment contents");
        Comment comment = Comment.from(addCommentDto);

        Assertions.assertThat(comment.getContents()).isEqualTo("comment contents");
    }

    @Test
    @DisplayName("Article 안에 comment 가 연결 되었을 때 테스트")
    void setCommentInArticle() {
        User user = new User("user1", "1234");
        AddArticleDto addArticleDto = new AddArticleDto("title", "contents");
        Article article = Article.of(addArticleDto, user);

        // 첫번째 댓글을 글에 연결
        AddCommentDto addCommentDto1 = new AddCommentDto(article.getId(), "comment contents1");
        Comment comment1 = Comment.from(addCommentDto1);

        comment1.setArticle(article);

        Assertions.assertThat(comment1.getArticle().getTitle()).isEqualTo("title");
        Assertions.assertThat(comment1.getContents()).isEqualTo("comment contents1");
        Assertions.assertThat(article.getCommentList().size()).isEqualTo(1);

        // 두번째 댓글을 글에 연결
        AddCommentDto addCommentDto2 = new AddCommentDto(article.getId(), "comment contents2");
        Comment comment2 = Comment.from(addCommentDto2);

        comment2.setArticle(article);

        Assertions.assertThat(comment2.getContents()).isEqualTo("comment contents2");
        Assertions.assertThat(article.getCommentList().size()).isEqualTo(2);
    }
}
