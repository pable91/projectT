package com.projectt.service;

import com.projectt.domain.User;
import com.projectt.domain.dto.LoginUserDto;
import com.projectt.domain.dto.SignupUserDto;
import com.projectt.repository.UserRepository;
import com.projectt.util.ErrorCode;
import com.projectt.util.exception.AlreadyExistUser;
import com.projectt.util.exception.NotFoundUserException;
import com.projectt.util.exception.WrongPasswordException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User signup(SignupUserDto signupUserDto) {
        String userId = signupUserDto.getUserid();

        Optional<User> findUser = userRepository.findByUserId(userId);
        if(findUser.isPresent()) {
            throw new AlreadyExistUser(ErrorCode.ALREADY_EXIST_USER);
        }

        String pw = passwordEncoder.encode(signupUserDto.getPw());
        User user = new User(userId, pw);
        userRepository.save(user);
        return user;
    }

    @Transactional(readOnly = true)
    public User login(LoginUserDto loginUserDto) {
        String userId = loginUserDto.getUserid();

        User findUser = userRepository.findByUserId(userId).orElseThrow(
                () -> new NotFoundUserException(ErrorCode.NOT_FOUND_USER)
        );

        if(!passwordEncoder.matches(loginUserDto.getPw(), findUser.getPw())) {
            throw new WrongPasswordException(ErrorCode.WRONG_PASSWORD);
        }

        return findUser;
    }
}
