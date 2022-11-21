package com.projectt.controller;

import com.projectt.domain.dto.request.AddArticleDto;
import com.projectt.domain.dto.request.LoginUserDto;
import com.projectt.domain.dto.request.SignupUserDto;
import com.projectt.domain.dto.request.UpdateArticleDto;
import com.projectt.domain.dto.response.ArticleResponseDto;
import com.projectt.domain.dto.response.ArticleViewResponseDto;
import com.projectt.domain.dto.response.TokenResponseDto;
import com.projectt.domain.dto.response.UserResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

public class ArticleControllerTest {

    @Test
    @DisplayName("글 관련 인수테스트")
    void articleTest() {
        // 회원가입
        SignupUserDto signupUserDto = new SignupUserDto("userid2", "1234", "kim");
        UserResponseDto userResponseDto = client()
                .post()
                .uri("/user/signup")
                .body(Mono.just(signupUserDto), SignupUserDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(UserResponseDto.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(userResponseDto.getUserid()).isEqualTo("userid2");

        // 로그인
        LoginUserDto loginUserDto = new LoginUserDto("userid2", "1234");
        TokenResponseDto tokenResponseDto = client()
                .post()
                .uri("user/signin")
                .body(Mono.just(loginUserDto), LoginUserDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(TokenResponseDto.class)
                .returnResult().getResponseBody();

        String token = "Bearer " + tokenResponseDto.getToken();

        // 글 등록
        AddArticleDto addArticleDto = new AddArticleDto("articleTitle1", "article Contents1");
        ArticleResponseDto articleResponseDto = client()
                .post()
                .uri("article")
                .header("Authorization", token)
                .body(Mono.just(addArticleDto), AddArticleDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ArticleResponseDto.class)
                .returnResult().getResponseBody();

        Long articleId = articleResponseDto.getArticleId();

        // 글 수정
        UpdateArticleDto updateArticleDto = new UpdateArticleDto(articleId, "articleTitle1 update", "article Contents1 update");
        client()
                .put()
                .uri("article")
                .header("Authorization", token)
                .body(Mono.just(updateArticleDto), UpdateArticleDto.class)
                .exchange()
                .expectStatus().isOk();

        // 글 수정 후 조회
        ArticleViewResponseDto articleViewResponseDto = client()
                .get()
                .uri("article/" + articleId)
                .header("Authorization", token)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ArticleViewResponseDto.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(articleViewResponseDto.getTitle()).isEqualTo("articleTitle1 update");
        Assertions.assertThat(articleViewResponseDto.getContents()).isEqualTo("article Contents1 update");

        // 글 삭제
        client()
                .delete()
                .uri("article/" + articleId)
                .header("Authorization", token)
                .exchange()
                .expectStatus().isOk();

        // 글 삭제 후 조회
        client()
                .get()
                .uri("article/" + articleId)
                .header("Authorization", token)
                .exchange()
                .expectStatus().isBadRequest();
    }

    private WebTestClient client() {
        return WebTestClient
                .bindToServer()
                .baseUrl("http://localhost:8080")
                .build();
    }
}
