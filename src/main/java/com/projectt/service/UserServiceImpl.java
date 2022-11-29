package com.projectt.service;

import com.projectt.domain.dto.request.LoginUserDto;
import com.projectt.domain.dto.request.SignupUserDto;
import com.projectt.domain.model.User;
import com.projectt.repository.UserRepository;
import com.projectt.common.ErrorCode;
import com.projectt.common.exception.AlreadyExistUser;
import com.projectt.common.exception.NotFoundUserException;
import com.projectt.common.exception.WrongPasswordException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User signup(final SignupUserDto signupUserDto) {

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

    @Override
    @Transactional(readOnly = true)
    public User login(final LoginUserDto loginUserDto) {
        String userId = loginUserDto.getUserid();

        User findUser = userRepository.findByUserId(userId).orElseThrow(
                () -> new NotFoundUserException(ErrorCode.NOT_FOUND_USER)
        );

        if(!passwordEncoder.matches(loginUserDto.getPw(), findUser.getPw())) {
            throw new WrongPasswordException(ErrorCode.WRONG_PASSWORD);
        }

        return findUser;
    }

    public void increasePointByAddArticle(final User user) {
        User findUser = userRepository.findByUserId(user.getUserId()).orElseThrow(
                () -> new NotFoundUserException(ErrorCode.NOT_FOUND_USER)
        );

        findUser.increasePointByAddArticle();
    }

    public void decreasePointByDeleteArticle(final User user) {
        User findUser = userRepository.findByUserId(user.getUserId()).orElseThrow(
                () -> new NotFoundUserException(ErrorCode.NOT_FOUND_USER)
        );

        findUser.decreasePointByAddArticle();
    }

    public void increasePointByAddComments(final User user, final int point) {
        User findUser = userRepository.findByUserId(user.getUserId()).orElseThrow(
                () -> new NotFoundUserException(ErrorCode.NOT_FOUND_USER)
        );

        findUser.increasePointByAddComments(point);
    }

    public void decreasePointByDeleteComments(final User user, final int point) {
        User findUser = userRepository.findByUserId(user.getUserId()).orElseThrow(
                () -> new NotFoundUserException(ErrorCode.NOT_FOUND_USER)
        );

        findUser.decreasePointByAddComments(point);
    }
}
