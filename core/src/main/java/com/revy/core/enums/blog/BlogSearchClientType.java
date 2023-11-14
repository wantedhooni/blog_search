package com.revy.core.enums.blog;

import lombok.Getter;

/**
 * Created by Revy on 2023.11.13
 * 블로그검색 클라이언트 타입
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
