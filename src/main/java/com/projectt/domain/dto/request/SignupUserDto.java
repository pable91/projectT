package com.projectt.domain.dto.request;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@Getter
public class SignupUserDto {

    @ApiModelProperty(value = "유저 아이디", example = "userId1", required = true)
    @NotBlank
    private final String userid;

    @ApiModelProperty(value = "패스워드", example = "1234", required = true)
    @NotBlank
    private final String pw;

    @ApiModelProperty(value = "유저 이름", example = "kim", required = true)
    @NotBlank
    private final String username;

    @Override
    public String toString() {
        return "SignupUserDto{" +
                "userid='" + userid + '\'' +
                ", pw='" + pw + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
