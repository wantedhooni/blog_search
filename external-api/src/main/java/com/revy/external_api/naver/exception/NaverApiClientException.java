package com.revy.external_api.naver.exception;

import lombok.Getter;

/**
 * Created by Revy on 2023.11.14
 * 네이버 API Client Exception
 */
@Getter
public class NaverApiClientException extends RuntimeException {

    private final String resErrorCode;
    private final String resErrorMessage;

    public NaverApiClientException(String resErrorCode, String resErrorMessage) {
        super(resErrorMessage);
        this.resErrorCode = resErrorCode;
        this.resErrorMessage = resErrorMessage;
    }
}
