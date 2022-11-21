package com.projectt.common.exception.handler;

import com.projectt.common.exception.CustomException;
import com.projectt.common.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> customException(CustomException e) {
        log.error(e.getErrorCode().getMsg());
        e.printStackTrace();
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }
}
