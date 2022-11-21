package com.projectt.common.response;

import com.projectt.common.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Builder
@Getter
public class ErrorResponse {
    private String result;
    private String errorMsg;
    public ErrorResponse(String result, String errorMsg) {
        this.result = result;
        this.errorMsg = errorMsg;
    }
    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode info) {
        return ResponseEntity
                .status(info.getHttpStatus())
                .body(ErrorResponse.builder()
                        .result("Fail")
                        .errorMsg(info.getMsg())
                        .build()
                );
    }
}
