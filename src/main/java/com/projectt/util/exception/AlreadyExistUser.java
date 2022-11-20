package com.projectt.util.exception;

import com.projectt.util.ErrorCode;

public class AlreadyExistUser extends CustomException {

    public AlreadyExistUser(ErrorCode code)  {
        super(code);
    }
}
