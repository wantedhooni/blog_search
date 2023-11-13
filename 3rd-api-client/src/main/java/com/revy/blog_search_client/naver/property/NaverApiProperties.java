package com.revy.blog_search_client.naver.property;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by Revy on 2023.11.13
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
