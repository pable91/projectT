package com.projectt.controller;

import com.projectt.domain.User;
import com.projectt.domain.dto.MyPointDto;
import com.projectt.util.ErrorCode;
import com.projectt.util.exception.NotFoundUserException;
import com.projectt.util.response.SuccessResponse;
import com.projectt.domain.dto.LoginUserDto;
import com.projectt.domain.dto.SignupUserDto;
import com.projectt.jwt.JwtTokenProvider;
import com.projectt.service.UserService;
import com.projectt.util.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity signUp(@RequestBody SignupUserDto signupUserDto) {
        System.out.println(signupUserDto.toString());

        User signup = userService.signup(signupUserDto);
        System.out.println(signup.toString());

        return SuccessResponse.toSignUpResponseEntity(signup);
    }

    @PostMapping("/signin")
    public ResponseEntity login(@RequestBody LoginUserDto loginUserDto) {
        System.out.println(loginUserDto.toString());

        User user = userService.login(loginUserDto);
        String token = jwtTokenProvider.createToken(user.getUserId());

        return SuccessResponse.toLoginResponseEntity(token);
    }

    @GetMapping("/profile")
    public ResponseEntity profile() {
        User currentUser = SecurityUtil.getCurrentUser()
                .orElseThrow(() -> new NotFoundUserException(ErrorCode.NOT_FOUND_USER));

        return SuccessResponse.toProfileResponseEntity(currentUser);
    }

    @GetMapping("/points")
    public ResponseEntity<MyPointDto> viewMyPoints() {
        User currentUser = SecurityUtil.getCurrentUser()
                .orElseThrow(() -> new NotFoundUserException(ErrorCode.NOT_FOUND_USER));

        return ResponseEntity.ok(MyPointDto.from(currentUser));
    }
}
