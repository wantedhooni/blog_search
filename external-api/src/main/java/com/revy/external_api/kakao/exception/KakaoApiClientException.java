package com.revy.external_api.kakao.exception;

import lombok.Getter;

/**
 * Created by Revy on 2023.11.14
 */
@Getter
public class KakaoApiClientException extends RuntimeException {

    private String resErrorType;
    private String resMessage;

    public KakaoApiClientException(String resErrorType, String resMessage) {
        super(resMessage);
        this.resErrorType = resErrorType;
        this.resMessage = resMessage;
    }
}
