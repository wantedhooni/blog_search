package com.revy.external_api.naver.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by Revy on 2023.11.13
 * 네이버 API Properties
 */

@Getter
@ConfigurationProperties("naver.api")
public class NaverApiProperties {
    private final String url;
    private final String clientId;
    private final String clientSecret;

    public NaverApiProperties(String url, String clientId, String clientSecret) {
        this.url = url;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }
}
