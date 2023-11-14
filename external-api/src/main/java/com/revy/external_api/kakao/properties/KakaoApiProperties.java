package com.revy.external_api.kakao.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by Revy on 2023.11.13
 */
@Getter
@ConfigurationProperties("kakao.api")
public class KakaoApiProperties {
    private final String url;
    private final String restApiKey;

    public KakaoApiProperties(String url, String restApiKey) {
        this.url = url;
        this.restApiKey = restApiKey;
    }
}
