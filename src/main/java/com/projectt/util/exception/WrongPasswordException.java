package com.projectt.util.exception;

import com.projectt.util.ErrorCode;

public class WrongPasswordException extends CustomException {

    public WrongPasswordException(ErrorCode code)  {
        super(code);
    }
}
