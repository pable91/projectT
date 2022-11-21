package com.projectt.common.exception;

import com.projectt.common.ErrorCode;

public class NotFoundCommentException extends CustomException {
    public NotFoundCommentException(ErrorCode errorCode) {
        super(errorCode);
    }
}
