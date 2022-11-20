package com.projectt.common.exception;

import com.projectt.common.ErrorCode;

public class NotFoundArticleException extends CustomException{

    public NotFoundArticleException(ErrorCode errorCode) {
        super(errorCode);
    }
}
