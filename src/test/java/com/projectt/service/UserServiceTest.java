package com.projectt.service;

import com.projectt.common.exception.AlreadyExistUser;
import com.projectt.common.exception.NotFoundUserException;
import com.projectt.common.exception.PointValueException;
import com.projectt.common.exception.WrongPasswordException;
import com.projectt.domain.dto.request.LoginUserDto;
import com.projectt.domain.dto.request.SignupUserDto;
import com.projectt.domain.model.User;
import com.projectt.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    @DisplayName("같은 아이디를 가진 유저를 회원가입시켜하면 예외를 던진다.")
    void alreadyUserExistException() {
        SignupUserDto signupUserDto = new SignupUserDto("userid", "1234","kim");
        userService.signup(signupUserDto);

        Assertions.assertThrows(AlreadyExistUser.class, () -> {
            userService.signup(signupUserDto);
        });
    }

    @Test
    @DisplayName("아이디가 없거나, 비밀번호가 틀리면 예외를 던진다.")
    void loginUserExceptionTest() {
        SignupUserDto signupUserDto = new SignupUserDto("userid", "1234","kim");
        userService.signup(signupUserDto);

        // 아이디가 틀린 경우
        LoginUserDto loginUserDto = new LoginUserDto("useriddddd", "1234");
        Assertions.assertThrows(NotFoundUserException.class, () -> {
            userService.login(loginUserDto);
        });

        // 비밀번호가 틀린경우
        LoginUserDto loginUserDto2 = new LoginUserDto("userid", "12345");
        Assertions.assertThrows(WrongPasswordException.class, () -> {
            userService.login(loginUserDto2);
        });
    }

    @Test
    @DisplayName("존재하지 않는 유저에 point를 가감하면 예외를 던진다.")
    void userPointExceptionTest() {
        User user = new User();

        Assertions.assertThrows(NotFoundUserException.class, () -> {
            userService.increasePointByAddArticle(user);
        });

        Assertions.assertThrows(NotFoundUserException.class, () -> {
            userService.decreasePointByDeleteArticle(user);
        });

        Assertions.assertThrows(NotFoundUserException.class, () -> {
            userService.increasePointByAddComments(user, 5);
        });

        Assertions.assertThrows(NotFoundUserException.class, () -> {
            userService.decreasePointByDeleteComments(user, 10);
        });
    }

    @Test
    @DisplayName("댓글 가감기능에서 0이하의 수를 매개변수로 보내면 예외를 던진다")
    void userPointExceptionTest2() {
        SignupUserDto signupUserDto = new SignupUserDto("userid", "1234","kim");
        userService.signup(signupUserDto);

        User user = new User("userid", "1234");

        Assertions.assertThrows(PointValueException.class, () -> {
            userService.increasePointByAddComments(user, -100);
        });

        Assertions.assertThrows(PointValueException.class, () -> {
            userService.decreasePointByDeleteComments(user, 0);
        });

        Assertions.assertThrows(PointValueException.class, () -> {
            userService.decreasePointByDeleteComments(user, -5);
        });
    }
}