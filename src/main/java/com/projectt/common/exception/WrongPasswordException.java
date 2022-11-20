package com.projectt.common.exception;

import com.projectt.common.ErrorCode;

public class WrongPasswordException extends CustomException {

    public WrongPasswordException(ErrorCode code)  {
        super(code);
    }
}
