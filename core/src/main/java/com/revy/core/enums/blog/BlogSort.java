package com.revy.core.enums.blog;

import lombok.Getter;

/**
 * Created by Revy
 * 블로그 검색 정렬 타입
 */
@Getter
public enum BlogSort {
    ACCURACY("정확도순"),
    RECENCY("최신순");


    private final String description;

    BlogSort(String description) {
        this.description = description;
    }
}
