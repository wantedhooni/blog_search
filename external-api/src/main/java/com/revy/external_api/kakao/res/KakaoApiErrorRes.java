package com.revy.external_api.kakao.res;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by Revy on 2023.11.14
 * 카카오 API 에러 응답 Response
 */
@Getter
@NoArgsConstructor
public class KakaoApiErrorRes {
    private String errorType;
    private String message;
}
