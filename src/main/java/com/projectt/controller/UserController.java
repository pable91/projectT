package com.projectt.controller;

import com.projectt.domain.dto.response.TokenResponseDto;
import com.projectt.domain.dto.response.UserResponseDto;
import com.projectt.domain.model.User;
import com.projectt.domain.dto.response.PointResponseDto;
import com.projectt.common.ErrorCode;
import com.projectt.common.exception.NotFoundUserException;
import com.projectt.domain.dto.LoginUserDto;
import com.projectt.domain.dto.SignupUserDto;
import com.projectt.jwt.JwtTokenProvider;
import com.projectt.service.UserService;
import com.projectt.common.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signUp(@RequestBody SignupUserDto signupUserDto) {
        log.info(signupUserDto.toString());

        User newUser = userService.signup(signupUserDto);
        log.info(newUser.toString());

        return ResponseEntity.ok(new UserResponseDto(newUser));
    }

    @PostMapping("/signin")
    public ResponseEntity<TokenResponseDto> login(@RequestBody LoginUserDto loginUserDto) {
        log.info(loginUserDto.toString());

        User user = userService.login(loginUserDto);
        String token = jwtTokenProvider.createToken(user.getUserId());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("JWT", token);

        return new ResponseEntity(new TokenResponseDto(token), httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<UserResponseDto> profile() {
        User currentUser = SecurityUtil.getCurrentUser()
                .orElseThrow(() -> new NotFoundUserException(ErrorCode.NOT_FOUND_USER));

        return ResponseEntity.ok(new UserResponseDto(currentUser));
    }

    @GetMapping("/points")
    public ResponseEntity<PointResponseDto> viewMyPoints() {
        User currentUser = SecurityUtil.getCurrentUser()
                .orElseThrow(() -> new NotFoundUserException(ErrorCode.NOT_FOUND_USER));

        return ResponseEntity.ok(new PointResponseDto(currentUser));
    }
}
