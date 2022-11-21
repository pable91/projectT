package com.projectt.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TokenResponseDto {
    public TokenResponseDto() {};

    private String token;
}
