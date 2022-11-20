package com.projectt.controller;

import com.projectt.domain.model.Article;
import com.projectt.domain.dto.AddArticleDto;
import com.projectt.domain.dto.UpdateArticleDto;
import com.projectt.service.ActionService;
import com.projectt.service.UserService;
import com.projectt.util.ErrorCode;
import com.projectt.util.exception.NotFoundUserException;
import com.projectt.util.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/action")
public class ActionController {

    private final ActionService actionService;
    private final UserService userService;

    @PostMapping("/article")
    public ResponseEntity<Long> writeArticle(@RequestBody AddArticleDto articleDto) {
        System.out.println(articleDto.toString());

        Article article = actionService.addArticle(articleDto);

        userService.increasePointByAddArticle(
                SecurityUtil.getCurrentUser()
                .orElseThrow(() -> new NotFoundUserException(ErrorCode.NOT_FOUND_USER))
        );

        return ResponseEntity.ok(article.getId());
    }

    @PutMapping("/article")
    public ResponseEntity<Long> updateArticle(@RequestBody UpdateArticleDto articleDto) {
        System.out.println(articleDto.toString());

        Article article = actionService.updateArticle(articleDto);

        return ResponseEntity.ok(article.getId());
    }
}
