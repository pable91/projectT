package com.projectt.controller;

import com.google.gson.Gson;
import com.projectt.domain.dto.request.SignupUserDto;
import com.projectt.domain.dto.response.UserResponseDto;
import com.projectt.domain.model.User;
import com.projectt.jwt.JwtTokenProvider;
import com.projectt.service.UserServiceImpl;
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
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest2 {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserServiceImpl userService;

//    @Mock
//    private JwtTokenProvider jwtTokenProvider;

    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void signupTest() throws Exception {
        SignupUserDto signupUserDto = new SignupUserDto("userid", "1234", "kim");
        User userResponse = new User("userid", "1233");

        doReturn(userResponse).when(userService)
                .signup(any(SignupUserDto.class));

        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/user/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(signupUserDto))
        );

        MvcResult resultActions1 = resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("userid", userResponse.getUserId()).exists())
                .andExpect(jsonPath("pw", userResponse.getPw()).exists()).andReturn();
    }
}
