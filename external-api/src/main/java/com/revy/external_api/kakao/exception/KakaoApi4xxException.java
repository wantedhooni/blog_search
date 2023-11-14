package com.revy.external_api.kakao.exception;

/**
 * Created by Revy on 2023.11.14
 * 카카오 4xx 에러 Exception
 */
public class KakaoApi4xxException extends KakaoApiClientException {
    public KakaoApi4xxException(String resErrorType, String resMessage) {
        super(resErrorType, resMessage);
    }
}
