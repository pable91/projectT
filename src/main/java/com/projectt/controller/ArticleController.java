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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    @ApiOperation(value = "글 등록", notes = "title과 contents를 필수값으로 입력해야한다")
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
    @ApiOperation(value = "글 수정", notes = "articleID 1이상의 값이여야한다")
    public ResponseEntity<ArticleResponseDto> updateArticle(@RequestBody @Valid UpdateArticleDto articleDto) {
        log.info(articleDto.toString());

        Article article = articleService.updateArticle(articleDto);

        return ResponseEntity.ok(new ArticleResponseDto(article));
    }

    @GetMapping("/article/{articleId}")
    @ApiOperation(value = "글 조회", notes = "articleID 1이상의 값이여야한다")
    public ResponseEntity<ArticleViewResponseDto> viewArticle(@ApiParam(value = "글 ID", example = "1") @PathVariable Long articleId) {
        log.info(String.valueOf(articleId));

        Article article = articleService.findById(articleId);

        ArticleViewResponseDto responseDto = ArticleViewResponseDto.of(article);

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/article/{articleId}")
    @ApiOperation(value = "글 삭제", notes = "articleID 1이상의 값이여야한다")
    public ResponseEntity<Long> deleteArticle(@ApiParam(value = "글 ID", example = "1") @PathVariable Long articleId) {
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
