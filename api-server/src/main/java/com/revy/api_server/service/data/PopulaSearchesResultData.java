package com.revy.api_server.service.data;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by Revy on 2023.11.12
 */

@Getter
@NoArgsConstructor
public class PopulaSearchesResultData {
    private int rank;
    private String keyword;
    private int count;

    public PopulaSearchesResultData(int rank, String keyword, int count) {
        this.rank = rank;
        this.keyword = keyword;
        this.count = count;
    }
}
