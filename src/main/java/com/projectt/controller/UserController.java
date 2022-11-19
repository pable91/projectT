package com.projectt.controller;

import com.projectt.domain.User;
import com.projectt.dto.LoginUserDto;
import com.projectt.dto.SignupUserDto;
import com.projectt.jwt.JwtTokenProvider;
import com.projectt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/signup")
    public String signUp(@RequestBody SignupUserDto signupUserDto) {
        System.out.println(signupUserDto.toString());

        User signup = userService.signup(signupUserDto);
        System.out.println(signup.toString());

        // TODO
        return "signup";
    }

    @PostMapping("/signin")
    public String login(@RequestBody LoginUserDto loginUserDto) {
        System.out.println(loginUserDto.toString());

        User user = userService.login(loginUserDto);
        String token = jwtTokenProvider.createToken(user.getUserId());

        return token;
    }
}
