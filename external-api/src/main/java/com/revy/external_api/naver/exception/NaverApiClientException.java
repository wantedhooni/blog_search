package com.revy.external_api.naver.exception;

import lombok.Getter;

/**
 * Created by Revy on 2023.11.14
 */
@Getter
public class NaverApiClientException extends RuntimeException {

    private String resErrorCode;
    private String resErrorMessage;

    public NaverApiClientException(String resErrorCode, String resErrorMessage) {
        super(resErrorMessage);
        this.resErrorCode = resErrorCode;
        this.resErrorMessage = resErrorMessage;
    }
}
