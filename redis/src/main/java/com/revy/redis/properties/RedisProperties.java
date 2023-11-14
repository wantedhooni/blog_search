package com.revy.redis.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by Revy on 2023.11.13
 */

@Getter
@ConfigurationProperties("redis")
public class RedisProperties {
    private String host;
    private Integer port;

    public RedisProperties(String host, Integer port) {
        this.host = host;
        this.port = port;
    }
}
