package com.projectt.controller;

import com.projectt.common.ErrorCode;
import com.projectt.common.exception.NotFoundUserException;
import com.projectt.common.security.SecurityUtil;
import com.projectt.domain.dto.request.AddArticleDto;
import com.projectt.domain.dto.request.UpdateArticleDto;
import com.projectt.domain.dto.response.ArticleResponseDto;
import com.projectt.domain.dto.response.ArticleViewResponseDto;
import com.projectt.domain.model.Article;
import com.projectt.service.ArticleService;
import com.projectt.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    private final UserService userService;

    @PostMapping("/article")
    public ResponseEntity<ArticleResponseDto> writeArticle(@RequestBody @Valid AddArticleDto articleDto) {
        log.info(articleDto.toString());

        Article article = articleService.addArticle(articleDto);

        userService.increasePointByAddArticle(
                SecurityUtil.getCurrentUser()
                .orElseThrow(() -> new NotFoundUserException(ErrorCode.NOT_FOUND_USER))
        );

        return ResponseEntity.ok(new ArticleResponseDto(article));
    }

    @PutMapping("/article")
    public ResponseEntity<ArticleResponseDto> updateArticle(@RequestBody @Valid UpdateArticleDto articleDto) {
        log.info(articleDto.toString());

        Article article = articleService.updateArticle(articleDto);

        return ResponseEntity.ok(new ArticleResponseDto(article));
    }

    @GetMapping("/article/{articleId}")
    public ResponseEntity<ArticleViewResponseDto> viewArticle(@PathVariable Long articleId) {
        log.info(String.valueOf(articleId));

        Article article = articleService.findById(articleId);

        ArticleViewResponseDto responseDto = ArticleViewResponseDto.of(article);

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/article/{articleId}")
    public ResponseEntity<Long> deleteArticle(@PathVariable Long articleId) {
        log.info(String.valueOf(articleId));

        deleteUserPoint(articleId);

        articleService.delete(articleId);

        return ResponseEntity.ok(1L);
    }

    private void deleteUserPoint(Long articleId) {
        Article article = articleService.findById(articleId);
        userService.decreasePointByDeleteArticle(article.getUser());
    }
}
