package com.revy.core.exception;

import com.revy.core.exception.common.CommonException;

/**
 * Created by Revy on 2023.11.13
 */
public class NotFoundServiceException extends CommonException {
    public NotFoundServiceException(String message) {
        super(message);
    }
}
