package com.revy.external_api.naver.res;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by Revy on 2023.11.14
 * 네이버 API 에러 Response
 */

@Getter
@NoArgsConstructor
public class NaverApiErrorRes {
    private String errorCode;
    private String errorMessage;
}
