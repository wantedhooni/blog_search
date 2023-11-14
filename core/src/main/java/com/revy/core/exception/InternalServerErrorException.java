package com.revy.core.exception;

import com.revy.core.exception.common.CommonException;

/**
 * Created by Revy on 2023.11.13
 */
public class InternalServerErrorException extends CommonException {
    public InternalServerErrorException(String message) {
        super(message);
    }
}
