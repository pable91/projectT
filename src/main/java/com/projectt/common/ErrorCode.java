package com.projectt.common;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    ALREADY_EXIST_USER(HttpStatus.BAD_REQUEST, "이미 존재하는 아이디입니다"),
    NOT_FOUND_USER(HttpStatus.BAD_REQUEST, "아이디를 확인해주세요"),
    WRONG_PASSWORD(HttpStatus.BAD_REQUEST, "잘못된 비밀번호를 입력하였습니다"),
    NOT_FOUND_ARTICLE(HttpStatus.BAD_REQUEST, "존재하지않는 Article 입니다");

    private HttpStatus httpStatus;
    private String msg;
}