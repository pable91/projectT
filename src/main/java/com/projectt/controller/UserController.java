package com.projectt.controller;

import com.projectt.domain.User;
import com.projectt.dto.UserDto;
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

    @PostMapping("/signup")
    public String signUp(@RequestBody UserDto userDto) {
        System.out.println(userDto.getUserid());
        System.out.println(userDto.getPw());
        System.out.println(userDto.getUsername());

        User signup = userService.signup(userDto);
        System.out.println(signup.toString());

        // TODO
        return "sa";
    }
}
