package com.revy.external_api.naver.exception;

/**
 * Created by Revy on 2023.11.14
 * 네이버 API 4xx 에러 Exception
 */
public class NaverApi4xxException extends NaverApiClientException {
    public NaverApi4xxException(String resErrorCode, String resErrorMessage) {
        super(resErrorCode, resErrorMessage);
    }
}
