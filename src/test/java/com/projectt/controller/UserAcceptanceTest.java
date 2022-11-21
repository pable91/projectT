package com.projectt.controller;

import com.projectt.common.security.SecurityUtil;
import com.projectt.domain.dto.LoginUserDto;
import com.projectt.domain.dto.SignupUserDto;
import com.projectt.domain.dto.response.PointResponseDto;
import com.projectt.domain.dto.response.TokenResponseDto;
import com.projectt.domain.dto.response.UserResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

public class UserAcceptanceTest {
    @Test
    @DisplayName("유저 관련된 인수테스트")
    void userTest() {
        // 회원가입
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

        // 로그인
        LoginUserDto loginUserDto = new LoginUserDto("userid", "1234");
        TokenResponseDto tokenResponseDto = client()
                .post()
                .uri("user/signin")
                .body(Mono.just(loginUserDto), LoginUserDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(TokenResponseDto.class)
                .returnResult().getResponseBody();

        String token = "Bearer " + tokenResponseDto.getToken();

        // 사용자 조회
        UserResponseDto userProfileResponse = client()
                .get()
                .uri("/user/profile")
                .header("Authorization", token)
                .exchange()
                .expectStatus().isOk()
                .expectBody(UserResponseDto.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(userProfileResponse.getUserid()).isEqualTo("userid");

        // 사용자 점수 조회
        PointResponseDto pointResponseDto = client()
                .get()
                .uri("/user/points")
                .header("Authorization", token)
                .exchange()
                .expectBody(PointResponseDto.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(pointResponseDto.getPoint()).isEqualTo(0);

        // 같은 아이디로 회원가입
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
