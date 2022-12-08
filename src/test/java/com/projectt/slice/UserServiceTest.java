package com.projectt.slice;

import com.projectt.common.exception.NotFoundUserException;
import com.projectt.common.exception.WrongPasswordException;
import com.projectt.domain.dto.request.LoginUserDto;
import com.projectt.domain.dto.request.SignupUserDto;
import com.projectt.domain.model.User;
import com.projectt.repository.UserRepository;
import com.projectt.service.UserServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Spy
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    void signupTest() {
        SignupUserDto signupUserDto = new SignupUserDto("idd", "1234", "kim");
        String encryptedPw = passwordEncoder.encode("1234");

        doReturn(new User("idd", encryptedPw)).when(userRepository)
                .save(any(User.class));

        User user = userService.signup(signupUserDto);
        Assertions.assertThat(user.getUserId()).isEqualTo("idd");
        Assertions.assertThat(user.getPw()).isEqualTo(encryptedPw);
    }

    @Test
    void loginTest() {
        LoginUserDto loginUserDto = new LoginUserDto("idd", "1234");
        String encryptedPw = passwordEncoder.encode(loginUserDto.getPw());

        doReturn(Optional.of(new User("idd", encryptedPw))).when(userRepository)
                .findByUserId(any(String.class));

        User user = userService.login(loginUserDto);
        Assertions.assertThat(user.getUserId()).isEqualTo("idd");
        Assertions.assertThat(user.getPw()).isEqualTo(encryptedPw);
    }

    @Test
    @DisplayName("유저를 찾을 없을때 발생하는 예외 테스트")
    void loginNotFoundUserExceptionTest() {
        LoginUserDto loginUserDto = new LoginUserDto("idd", "1234");

        doThrow(NotFoundUserException.class).when(userRepository)
                .findByUserId(any(String.class));

        org.junit.jupiter.api.Assertions.assertThrows(NotFoundUserException.class,
                () -> userService.login(loginUserDto));
    }

    @Test
    @DisplayName("패스워드가 틀렸을때 발생하는 예외 테스트")
    void loginWrongPasswordExceptionTest() {
        LoginUserDto loginUserDto = new LoginUserDto("idd", "1234");
        String encryptedPw = passwordEncoder.encode("4321");

        doReturn(Optional.of(new User("idd", encryptedPw))).when(userRepository)
                .findByUserId(any(String.class));

        org.junit.jupiter.api.Assertions.assertThrows(WrongPasswordException.class,
                () -> userService.login(loginUserDto));
    }
}
