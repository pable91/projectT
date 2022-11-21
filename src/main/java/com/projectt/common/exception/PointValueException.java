package com.projectt.common.exception;

import com.projectt.common.ErrorCode;

public class PointValueException extends CustomException {
    public PointValueException(ErrorCode errorCode) {
        super(errorCode);
    }
}
