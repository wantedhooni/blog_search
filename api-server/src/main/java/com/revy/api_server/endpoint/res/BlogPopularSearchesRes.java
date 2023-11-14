package com.revy.api_server.endpoint.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Revy on 2023.11.12
 * 인기 검색어 결과 Response
 */
@Getter
@NoArgsConstructor
public class BlogPopularSearchesRes {

    @JsonProperty("data")
    private List<BlogPopularSearchesResData> data;

    public BlogPopularSearchesRes(List<BlogPopularSearchesResData> data) {
        this.data = data;
    }

    @Getter
    @NoArgsConstructor
    public static class BlogPopularSearchesResData {
        @JsonProperty("rank")
        private int rank;
        @JsonProperty("keyword")
        private String keyword;
        @JsonProperty("count")
        private int count;

        public BlogPopularSearchesResData(int rank, String keyword, int count) {
            this.rank = rank;
            this.keyword = keyword;
            this.count = count;
        }
    }
}
