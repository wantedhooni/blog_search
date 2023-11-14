package com.revy.api_server.service.data;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by Revy on 2023.11.12
 * 인기 검색어 결과 DTO
 */

@Getter
@NoArgsConstructor
public class PopularSearchesResultData {
    private int rank;
    private String keyword;
    private int count;

    public PopularSearchesResultData(int rank, String keyword, int count) {
        this.rank = rank;
        this.keyword = keyword;
        this.count = count;
    }
}
