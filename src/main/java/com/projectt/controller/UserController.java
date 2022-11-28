package com.projectt.controller;

import com.projectt.common.ErrorCode;
import com.projectt.common.exception.NotFoundUserException;
import com.projectt.common.security.SecurityUtil;
import com.projectt.domain.dto.request.LoginUserDto;
import com.projectt.domain.dto.request.SignupUserDto;
import com.projectt.domain.dto.response.PointResponseDto;
import com.projectt.domain.dto.response.TokenResponseDto;
import com.projectt.domain.dto.response.UserResponseDto;
import com.projectt.domain.model.User;
import com.projectt.jwt.JwtTokenProvider;
import com.projectt.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/signup")
    @ApiOperation(value = "회원가입", notes = "userId와 pw를 필수값으로 입력해야한다")
    public ResponseEntity<UserResponseDto> signUp(@RequestBody @Valid final SignupUserDto signupUserDto) {
        log.info(signupUserDto.toString());

        User newUser = userService.signup(signupUserDto);
        log.info(newUser.toString());

        return ResponseEntity.ok(new UserResponseDto(newUser));
    }

    @PostMapping("/signin")
    @ApiOperation(value = "로그인", notes = "userId와 pw를 필수값으로 입력해야한다")
    public ResponseEntity<TokenResponseDto> login(@RequestBody @Valid final LoginUserDto loginUserDto) {
        log.info(loginUserDto.toString());

        User user = userService.login(loginUserDto);
        String token = jwtTokenProvider.createToken(user.getUserId());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("JWT", token);

        return new ResponseEntity(new TokenResponseDto(token), httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/profile")
    @ApiOperation(value = "유저 프로필 조회")
    public ResponseEntity<UserResponseDto> profile() {
        User currentUser = SecurityUtil.getCurrentUser()
                .orElseThrow(() -> new NotFoundUserException(ErrorCode.NOT_FOUND_USER));

        return ResponseEntity.ok(new UserResponseDto(currentUser));
    }

    @GetMapping("/points")
    @ApiOperation(value = "유저 포인트 조회")
    public ResponseEntity<PointResponseDto> viewMyPoints() {
        User currentUser = SecurityUtil.getCurrentUser()
                .orElseThrow(() -> new NotFoundUserException(ErrorCode.NOT_FOUND_USER));

        return ResponseEntity.ok(new PointResponseDto(currentUser));
    }
}
