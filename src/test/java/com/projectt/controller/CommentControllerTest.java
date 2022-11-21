package com.projectt.controller;

import com.projectt.common.security.SecurityUtil;
import com.projectt.domain.dto.AddArticleDto;
import com.projectt.domain.dto.AddCommentDto;
import com.projectt.domain.dto.LoginUserDto;
import com.projectt.domain.dto.SignupUserDto;
import com.projectt.domain.dto.response.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

public class CommentControllerTest {

    @Test
    @DisplayName("댓글 관련 인수테스트")
    void commentTest() {
        // 회원가입
        SignupUserDto signupUserDto = new SignupUserDto("userid3", "1234", "kim");
        UserResponseDto userResponseDto = client()
                .post()
                .uri("/user/signup")
                .body(Mono.just(signupUserDto), SignupUserDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(UserResponseDto.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(userResponseDto.getUserid()).isEqualTo("userid3");

        // 로그인
        LoginUserDto loginUserDto = new LoginUserDto("userid3", "1234");
        TokenResponseDto tokenResponseDto = client()
                .post()
                .uri("/user/signin")
                .body(Mono.just(loginUserDto), LoginUserDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(TokenResponseDto.class)
                .returnResult().getResponseBody();

        String token = "Bearer " + tokenResponseDto.getToken();

        // 글 등록
        AddArticleDto addArticleDto = new AddArticleDto("articleTitle with comment", "articleContents with comment");
        ArticleResponseDto articleResponseDto = client()
                .post()
                .uri("/article")
                .header("Authorization", token)
                .body(Mono.just(addArticleDto), AddArticleDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ArticleResponseDto.class)
                .returnResult().getResponseBody();

        Long articleId = articleResponseDto.getArticleId();

        // 코멘트 등록
        AddCommentDto addCommentDto = new AddCommentDto(articleId, "comment@@@@");
        CommentsResponseDto commentsResponseDto = client()
                .post()
                .uri("/comments")
                .header("Authorization", token)
                .body(Mono.just(addCommentDto), AddCommentDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(CommentsResponseDto.class)
                .returnResult().getResponseBody();

        // 코멘트 등록으로 인한 점수 조회
        PointResponseDto pointResponseDto = client()
                .get()
                .uri("/user/points")
                .header("Authorization", token)
                .exchange()
                .expectBody(PointResponseDto.class)
                .returnResult().getResponseBody();

        // 글 등록 3점 + 코멘트 등록자 2점 + 글 등록 원작자 1점 = 6점
        Assertions.assertThat(pointResponseDto.getPoint()).isEqualTo(6);

        // 코멘트 삭제
        client()
                .delete()
                .uri("/comments/" + commentsResponseDto.getCommentsId())
                .header("Authorization", token)
                .exchange()
                .expectStatus().isOk();

        pointResponseDto = client()
                .get()
                .uri("/user/points")
                .header("Authorization", token)
                .exchange()
                .expectBody(PointResponseDto.class)
                .returnResult().getResponseBody();

        // 기존 6점 - 코멘트 삭제로 인한 3점 = 3
        Assertions.assertThat(pointResponseDto.getPoint()).isEqualTo(3);
    }

    private WebTestClient client() {
        return WebTestClient
                .bindToServer()
                .baseUrl("http://localhost:8080")
                .build();
    }
}
