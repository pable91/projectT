package com.projectt.controller;

import com.projectt.domain.dto.response.ArticleResponseDto;
import com.projectt.domain.model.Article;
import com.projectt.domain.dto.AddArticleDto;
import com.projectt.domain.dto.UpdateArticleDto;
import com.projectt.service.ArticleService;
import com.projectt.service.UserService;
import com.projectt.common.ErrorCode;
import com.projectt.common.exception.NotFoundUserException;
import com.projectt.common.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final UserService userService;

    @PostMapping("/article")
    public ResponseEntity<ArticleResponseDto> writeArticle(@RequestBody AddArticleDto articleDto) {
        log.info(articleDto.toString());

        Article article = articleService.addArticle(articleDto);

        userService.increasePointByAddArticle(
                SecurityUtil.getCurrentUser()
                .orElseThrow(() -> new NotFoundUserException(ErrorCode.NOT_FOUND_USER))
        );

        return ResponseEntity.ok(new ArticleResponseDto(article));
    }

    @PutMapping("/article")
    public ResponseEntity<ArticleResponseDto> updateArticle(@RequestBody UpdateArticleDto articleDto) {
        log.info(articleDto.toString());

        Article article = articleService.updateArticle(articleDto);

        return ResponseEntity.ok(new ArticleResponseDto(article));
    }

    @GetMapping("/article/{articleId}")
    public ResponseEntity<Article> viewArticle(@PathVariable Long articleId) {
        log.info(String.valueOf(articleId));

        Article article = articleService.findById(articleId);

        log.info(article.toString());

        // TODO : commentId도 반환해야함.
        return ResponseEntity.ok(article);
    }

    @DeleteMapping("/article/{articleId}")
    public ResponseEntity<ArticleResponseDto> deleteArticle(@PathVariable Long articleId) {
        log.info(String.valueOf(articleId));

        deleteUserPoint(articleId);

        Article deleteArticle = articleService.delete(articleId);

        // TODO : 삭제 카운트가 뭐지?
        return ResponseEntity.ok(new ArticleResponseDto(deleteArticle));
    }

    private void deleteUserPoint(Long articleId) {
        Article article = articleService.findById(articleId);
        userService.decreasePointByDeleteArticle(article.getUser());
    }
}
