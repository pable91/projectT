package com.projectt.domain.dto.response;

import com.projectt.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserResponseDto {

    private String userid;
    private String pw;

    public UserResponseDto(User currentUser) {
        this.userid = currentUser.getUserId();
        this.pw = currentUser.getPw();
    }
}
