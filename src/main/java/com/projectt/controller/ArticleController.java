package com.projectt.controller;

import com.projectt.common.ErrorCode;
import com.projectt.common.exception.NotFoundUserException;
import com.projectt.common.security.SecurityUtil;
import com.projectt.domain.dto.request.AddArticleDto;
import com.projectt.domain.dto.request.UpdateArticleDto;
import com.projectt.domain.dto.response.ArticleResponseDto;
import com.projectt.domain.dto.response.ArticleViewResponseDto;
import com.projectt.domain.model.Article;
import com.projectt.service.ArticleServiceImpl;
import com.projectt.service.UserServiceImpl;
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
    private final ArticleServiceImpl articleServiceImpl;
    private final UserServiceImpl userServiceImpl;

    @PostMapping("/article")
    @ApiOperation(value = "글 등록", notes = "title과 contents를 필수값으로 입력해야한다")
    public ResponseEntity<ArticleResponseDto> writeArticle(@RequestBody @Valid final AddArticleDto articleDto) {
        log.info(articleDto.toString());

        Article article = articleServiceImpl.addArticle(articleDto);

        userServiceImpl.increasePointByAddArticle(
                SecurityUtil.getCurrentUser()
                .orElseThrow(() -> new NotFoundUserException(ErrorCode.NOT_FOUND_USER))
        );

        return ResponseEntity.ok(new ArticleResponseDto(article));
    }

    @PutMapping("/article")
    @ApiOperation(value = "글 수정", notes = "articleID 1이상의 값이여야한다")
    public ResponseEntity<ArticleResponseDto> updateArticle(@RequestBody @Valid final UpdateArticleDto articleDto) {
        log.info(articleDto.toString());

        Article article = articleServiceImpl.updateArticle(articleDto);

        return ResponseEntity.ok(new ArticleResponseDto(article));
    }

    @GetMapping("/article/{articleId}")
    @ApiOperation(value = "글 조회", notes = "articleID 1이상의 값이여야한다")
    public ResponseEntity<ArticleViewResponseDto> viewArticle(@ApiParam(value = "글 ID", example = "1") @PathVariable final Long articleId) {
        log.info(String.valueOf(articleId));

        Article article = articleServiceImpl.findById(articleId);

        ArticleViewResponseDto responseDto = ArticleViewResponseDto.of(article);

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/article/{articleId}")
    @ApiOperation(value = "글 삭제", notes = "articleID 1이상의 값이여야한다")
    public ResponseEntity<Long> deleteArticle(@ApiParam(value = "글 ID", example = "1") @PathVariable final Long articleId) {
        log.info(String.valueOf(articleId));

        deleteUserPoint(articleId);

        articleServiceImpl.delete(articleId);

        return ResponseEntity.ok(1L);
    }

    private void deleteUserPoint(final Long articleId) {
        Article article = articleServiceImpl.findById(articleId);
        userServiceImpl.decreasePointByDeleteArticle(article.getUser());
    }
}
