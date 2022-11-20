package com.projectt.controller;

import com.projectt.domain.model.Article;
import com.projectt.dto.AddArticleDto;
import com.projectt.service.ActionService;
import com.projectt.service.UserService;
import com.projectt.util.ErrorCode;
import com.projectt.util.exception.NotFoundUserException;
import com.projectt.util.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/action")
public class ActionController {

    private final ActionService actionService;
    private final UserService userService;

    @PostMapping("/article")
    public ResponseEntity WriteArticle(@RequestBody AddArticleDto articleDto) {

        System.out.println(articleDto.toString());

        Article article = actionService.addArticle(articleDto);

        userService.increasePointByAddArticle(
                SecurityUtil.getCurrentUser()
                .orElseThrow(() -> new NotFoundUserException(ErrorCode.NOT_FOUND_USER))
        );

        System.out.println(article.toString());

        return ResponseEntity.ok(article.getId());
    }
}
