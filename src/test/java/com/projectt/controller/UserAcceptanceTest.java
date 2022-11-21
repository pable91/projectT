package com.projectt.controller;

import com.projectt.domain.dto.LoginUserDto;
import com.projectt.domain.dto.SignupUserDto;
import com.projectt.domain.dto.response.UserResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

public class UserAcceptanceTest {

    @Test
    @DisplayName("유저 회원가입/로그인, 같은 아이디로 가입시 잘못된 요청")
    void userTest() {
        SignupUserDto signupUserDto = new SignupUserDto("userid", "1234", "kim");
        UserResponseDto userResponseDto = client()
                .post()
                .uri("/user/signup")
                .body(Mono.just(signupUserDto), SignupUserDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(UserResponseDto.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(userResponseDto.getUserid()).isEqualTo("userid");

        LoginUserDto loginUserDto = new LoginUserDto("userid", "1234");
        client()
                .post()
                .uri("user/signin")
                .body(Mono.just(loginUserDto), LoginUserDto.class)
                .exchange()
                .expectStatus().isOk();

        SignupUserDto signupUserDto2 = new SignupUserDto("userid", "1234", "kim");
        client()
                .post()
                .uri("/user/signup")
                .body(Mono.just(signupUserDto2), SignupUserDto.class)
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
