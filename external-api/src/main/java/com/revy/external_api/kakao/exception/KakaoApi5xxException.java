package com.revy.external_api.kakao.exception;

/**
 * Created by Revy on 2023.11.14
 * 카카오 5xx 에러 Exception
 */
public class KakaoApi5xxException extends KakaoApiClientException {
    public KakaoApi5xxException(String resErrorType, String resMessage) {
        super(resErrorType, resMessage);
    }
}
