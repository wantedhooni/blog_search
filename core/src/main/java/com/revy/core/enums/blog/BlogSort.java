package com.revy.core.enums.blog;

import lombok.Getter;

@Getter
public enum BlogSort {
    ACCURACY("정확도순"),
    RECENCY("최신순");


    private final String description;

    BlogSort(String description) {
        this.description = description;
    }
}
