package com.revy.api_server.exception.handler;

import com.revy.api_server.exception.enums.ErrorCode;
import com.revy.api_server.exception.handler.res.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

/**
 * Created by Revy on 2023.11.14
 */
@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler({BindException.class, WebExchangeBindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleBindException(BindingResult bindingResult) {
        return ErrorResponse.of(ErrorCode.BAD_REQUEST.getCode(), bindingResult.getFieldError().getDefaultMessage());
    }
}
