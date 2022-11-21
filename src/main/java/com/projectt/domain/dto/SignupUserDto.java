package com.projectt.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SignupUserDto {
    private String userid;
    private String pw;
    private String username;
    @Override
    public String toString() {
        return "SignupUserDto{" +
                "userid='" + userid + '\'' +
                ", pw='" + pw + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
