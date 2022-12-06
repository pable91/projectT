package com.projectt.service;

import com.projectt.domain.dto.request.LoginUserDto;
import com.projectt.domain.dto.request.SignupUserDto;
import com.projectt.domain.model.User;

public interface UserService {

    User signup(final SignupUserDto signupUserDto);
    User login(final LoginUserDto loginUserDto);
    User getCurrentUser();
}
