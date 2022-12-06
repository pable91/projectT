package com.projectt.domain.dto.request;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SignupUserDto {

    @ApiModelProperty(value = "유저 아이디", example = "userId1", required = true)
    @NotBlank
    private String userid;

    @ApiModelProperty(value = "패스워드", example = "1234", required = true)
    @NotBlank
    private String pw;

    @ApiModelProperty(value = "유저 이름", example = "kim", required = true)
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
