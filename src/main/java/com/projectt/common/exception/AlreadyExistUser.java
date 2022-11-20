package com.projectt.common.exception;

import com.projectt.common.ErrorCode;

public class AlreadyExistUser extends CustomException {

    public AlreadyExistUser(ErrorCode code)  {
        super(code);
    }
}
