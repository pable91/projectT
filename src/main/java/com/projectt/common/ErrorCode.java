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
    NOT_FOUND_ARTICLE(HttpStatus.BAD_REQUEST, "존재하지않는 Article 입니다"),
    NOT_FOUND_COMMENT(HttpStatus.BAD_REQUEST, "존재하지않는 Comment 입니다"),
    POINT_VALUE_ONLY_POSITIVE(HttpStatus.BAD_REQUEST, "point 값은 양수만 가능합니다");

    private HttpStatus httpStatus;
    private String msg;
}