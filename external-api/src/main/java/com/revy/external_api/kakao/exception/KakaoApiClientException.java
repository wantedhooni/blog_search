package com.revy.external_api.kakao.exception;

import lombok.Getter;

/**
 * Created by Revy on 2023.11.14
 * 카카오 Api Client Exception
 */
@Getter
public class KakaoApiClientException extends RuntimeException {

    private final String resErrorType;
    private final String resMessage;

    public KakaoApiClientException(String resErrorType, String resMessage) {
        super(resMessage);
        this.resErrorType = resErrorType;
        this.resMessage = resMessage;
    }
}
