package com.projectt.dto;

import lombok.Getter;

@Getter
public class LoginUserDto {
    private String userid;
    private String pw;

    @Override
    public String toString() {
        return "LoginUserDto{" +
                "userid='" + userid + '\'' +
                ", pw='" + pw + '\'' +
                '}';
    }
}
