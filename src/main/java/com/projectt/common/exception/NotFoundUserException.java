package com.projectt.common.exception;

import com.projectt.common.ErrorCode;

public class NotFoundUserException extends CustomException {

    public NotFoundUserException(ErrorCode code)  {
        super(code);
    }
}
