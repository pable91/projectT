package com.projectt.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
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
