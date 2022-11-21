package com.projectt.domain.dto.response;



import com.projectt.domain.model.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class ArticleViewResponseDto {
    private Long articleId;
    private List<Long> commentIds;
    public static ArticleViewResponseDto of(Article article) {
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < article.getCommentList().size(); ++i) {
            list.add(article.getCommentList().get(i).getId());
        }

        return new ArticleViewResponseDto(article.getId(), list);
    }
}
