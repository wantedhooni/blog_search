package com.revy.blog_search_client.kakao.property;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

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
