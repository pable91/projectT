package com.projectt.controller;

import com.projectt.domain.dto.response.TokenResponseDto;
import com.projectt.domain.dto.response.UserResponseDto;
import com.projectt.domain.model.User;
import com.projectt.domain.dto.response.PointResponseDto;
import com.projectt.util.ErrorCode;
import com.projectt.util.exception.NotFoundUserException;
import com.projectt.util.response.SuccessResponse;
import com.projectt.domain.dto.LoginUserDto;
import com.projectt.domain.dto.SignupUserDto;
import com.projectt.jwt.JwtTokenProvider;
import com.projectt.service.UserService;
import com.projectt.util.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signUp(@RequestBody SignupUserDto signupUserDto) {
        System.out.println(signupUserDto.toString());

        User newUser = userService.signup(signupUserDto);
        System.out.println(newUser.toString());

        return ResponseEntity.ok(new UserResponseDto(newUser));
    }

    @PostMapping("/signin")
    public ResponseEntity<TokenResponseDto> login(@RequestBody LoginUserDto loginUserDto) {
        System.out.println(loginUserDto.toString());

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
