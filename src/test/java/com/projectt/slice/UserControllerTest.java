package com.projectt.slice;

import com.google.gson.Gson;
import com.projectt.controller.UserController;
import com.projectt.domain.dto.request.LoginUserDto;
import com.projectt.domain.dto.request.SignupUserDto;
import com.projectt.domain.dto.response.TokenResponseDto;
import com.projectt.domain.dto.response.UserResponseDto;
import com.projectt.domain.model.User;
import com.projectt.jwt.JwtTokenProvider;
import com.projectt.service.UserServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserServiceImpl userService;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void signupTest() throws Exception {
        /**
         * give
         */
        SignupUserDto signupUserDto = new SignupUserDto("userid", "1234", "kim");
        User userResponse = new User("userid", "1234");

        // 이걸 준비시켜놓겠다.
        doReturn(userResponse).when(userService)
                .signup(any(SignupUserDto.class));

        /**
         * when
         */
        // url 보내기
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/user/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(signupUserDto))
        );

        /**
         * then
         */
        // 상태값과 값이 존재하는지 check
        MvcResult mvcResult = resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("userid", userResponse.getUserId()).exists())
                .andExpect(jsonPath("pw", userResponse.getPw()).exists()).andReturn();

        // 값 확인
        UserResponseDto userResponseDto = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), UserResponseDto.class);
        Assertions.assertThat(userResponseDto.getUserid()).isEqualTo("userid");
        Assertions.assertThat(userResponseDto.getPw()).isEqualTo("1234");
    }

    @Test
    void loginTest() throws Exception {
        LoginUserDto loginUserDto = new LoginUserDto("userid", "1234");

        User userResponse = new User("userid", "1234");
        doReturn(userResponse).when(userService)
                .login(any(LoginUserDto.class));

        String token = "";
        doReturn(token).when(jwtTokenProvider)
                .createToken(any(String.class));

        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/user/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(loginUserDto))
        );

        MvcResult mvcResult = resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("token", token).exists()).andReturn();

        TokenResponseDto tokenResponseDto = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), TokenResponseDto.class);
        Assertions.assertThat(tokenResponseDto.getToken()).isEqualTo(token);
    }

    @Test
    void profile() throws Exception {
        User userResponse = new User("userid", "1234");

        doReturn(userResponse).when(userService)
                .getCurrentUser();

        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/user/profile")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        MvcResult mvcResult = resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("userid", userResponse.getUserId()).exists())
                .andExpect(jsonPath("pw", userResponse.getPw()).exists()).andReturn();

        UserResponseDto userResponseDto = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), UserResponseDto.class);
        Assertions.assertThat(userResponseDto.getUserid()).isEqualTo(userResponse.getUserId());
        Assertions.assertThat(userResponseDto.getPw()).isEqualTo(userResponse.getPw());
    }
}
