package com.revy.api_server.handler;


import com.revy.api_server.handler.res.ErrorResponse;
import com.revy.core.enums.common.ErrorCode;
import com.revy.core.exception.InternalServerErrorException;
import com.revy.core.exception.NotFoundServiceException;
import com.revy.core.exception.common.CommonException;
import com.revy.external_api.kakao.exception.KakaoApi4xxException;
import com.revy.external_api.kakao.exception.KakaoApi5xxException;
import com.revy.external_api.naver.exception.NaverApi4xxException;
import com.revy.external_api.naver.exception.NaverApi5xxException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * Created by Revy on 2023.11.14
 */
@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler({BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleBindException(BindingResult bindingResult) {
        return ErrorResponse.of(
                ErrorCode.BAD_REQUEST.getCode(),
                Objects.requireNonNullElse(bindingResult.getFieldError().getDefaultMessage(), ErrorCode.BAD_REQUEST.getDescription())
        );
    }

    @ExceptionHandler({KakaoApi4xxException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleKakaoApi4xxException(KakaoApi4xxException e) {
        log.error("KakaoApi4xxException : ", e);
        return ErrorResponse.of(ErrorCode.BAD_REQUEST);
    }

    @ExceptionHandler({NaverApi4xxException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleNaverApi4xxException(NaverApi4xxException e) {
        log.error("NaverApi4xxException : ", e);
        return ErrorResponse.of(ErrorCode.BAD_REQUEST);
    }

    @ExceptionHandler({KakaoApi5xxException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ErrorResponse handleKakaoApi5xxException(KakaoApi5xxException e) {
        log.error("KakaoApi5xxException : ", e);
        return ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({NaverApi5xxException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ErrorResponse handleNaverApi5xxException(NaverApi5xxException e) {
        log.error("NaverApi5xxException : ", e);
        return ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({NotFoundServiceException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ErrorResponse handleNotFoundServiceException(NotFoundServiceException e) {
        log.error("NotFoundServiceException : ", e);
        return ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
    }

    @ExceptionHandler({InternalServerErrorException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ErrorResponse handleInternalServerErrorException(InternalServerErrorException e) {
        log.error("InternalServerErrorException : ", e);
        return ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
    }

    @ExceptionHandler({Exception.class, CommonException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ErrorResponse handleException(Exception e) {
        log.error("Exception : ", e);
        return ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);
    }


}
