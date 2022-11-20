package com.projectt.domain.dto;

import lombok.Getter;

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
