package com.revy.external_api.naver.exception;

/**
 * Created by Revy on 2023.11.14
 * 네이버 API 5xx 에러 Exception
 */
public class NaverApi5xxException extends NaverApiClientException {
    public NaverApi5xxException(String resErrorCode, String resErrorMessage) {
        super(resErrorCode, resErrorMessage);
    }
}
