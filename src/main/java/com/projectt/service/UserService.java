package com.projectt.service;

import com.projectt.domain.User;
import com.projectt.dto.LoginUserDto;
import com.projectt.dto.SignupUserDto;
import com.projectt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User signup(SignupUserDto signupUserDto) {
        String userId = signupUserDto.getUserid();

        Optional<User> findUser = userRepository.findByUserId(userId);
        if(findUser.isPresent()) {
            throw new RuntimeException("이미 존재함");
        }

        String pw = passwordEncoder.encode(signupUserDto.getPw());
        User user = new User(userId, pw);
        userRepository.save(user);
        return user;
    }

    public User login(LoginUserDto loginUserDto) {
        String userId = loginUserDto.getUserid();

        User findUser = userRepository.findByUserId(userId).orElseThrow(
                () -> new RuntimeException("그런 아이디는 존재하지 않음")
        );

        if(!passwordEncoder.matches(loginUserDto.getPw(), findUser.getPw())) {
            throw new RuntimeException("비밀번호를 잘못 입력하였습니다");
        }

        return findUser;
    }

}
