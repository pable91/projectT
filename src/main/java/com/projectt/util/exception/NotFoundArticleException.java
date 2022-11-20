package com.projectt.util.exception;

import com.projectt.util.ErrorCode;

public class NotFoundArticleException extends CustomException{

    public NotFoundArticleException(ErrorCode errorCode) {
        super(errorCode);
    }
}
