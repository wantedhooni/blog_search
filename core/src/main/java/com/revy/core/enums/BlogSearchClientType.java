package com.revy.core.enums;

import lombok.Getter;

/**
 * Created by Revy on 2023.11.13
 */
@Getter
public enum BlogSearchClientType {
    KAKAO("카카오"),
    NAVER("네이버");

    private final String description;

    BlogSearchClientType(String description) {
        this.description = description;
    }
}
