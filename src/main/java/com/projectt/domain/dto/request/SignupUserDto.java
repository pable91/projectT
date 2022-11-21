package com.projectt.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@Getter
public class SignupUserDto {

    @NotBlank
    private String userid;

    @NotBlank
    private String pw;

    @NotBlank
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
