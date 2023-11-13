package com.revy.api_server.exception;

import com.revy.core.exception.CommonException;

/**
 * Created by Revy on 2023.11.13
 */
public class InternalServerErrorException extends CommonException {
    public InternalServerErrorException(String message) {
        super(message);
    }
}
