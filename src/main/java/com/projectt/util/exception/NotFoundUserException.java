package com.projectt.util.exception;

import com.projectt.util.ErrorCode;

public class NotFoundUserException extends CustomException {

    public NotFoundUserException(ErrorCode code)  {
        super(code);
    }
}
