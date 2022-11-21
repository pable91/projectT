package com.projectt.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
public class LoginUserDto {

    @NotBlank
    private String userid;

    @NotBlank
    private String pw;

    @Override
    public String toString() {
        return "LoginUserDto{" +
                "userid='" + userid + '\'' +
                ", pw='" + pw + '\'' +
                '}';
    }
}
