package com.revy.api_server.service.data;


import com.revy.core.enums.blog.BlogSort;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by Revy on 2023.11.12
 * 블로그 검색 조건 DTO
 */

@Getter
@NoArgsConstructor
@ToString
public class BlogSearchConditionData {
    private String keyword;
    private int page;
    private int size;
    private BlogSort sort;

    @Builder
    public BlogSearchConditionData(String keyword, int page, int size, BlogSort sort) {
        this.keyword = keyword;
        this.page = page;
        this.size = size;
        this.sort = sort;
    }
}
