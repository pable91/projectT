package com.projectt.domain.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LoginUserDto {

    @ApiModelProperty(value = "유저 아이디", example = "userId1", required = true)
    @NotBlank
    private String userid;

    @ApiModelProperty(value = "패스워드", example = "1234", required = true)
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
