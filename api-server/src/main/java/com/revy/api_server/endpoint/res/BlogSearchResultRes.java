package com.revy.api_server.endpoint.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Revy on 2023.11.12
 * 블로그 검색 결과 Response
 */

@Getter
@NoArgsConstructor
public class BlogSearchResultRes {
    @JsonProperty("currentPage")
    private int currentPage;
    @JsonProperty("totalCount")
    private int totalCount;
    @JsonProperty("pageableCount")
    private int pageableCount;
    @JsonProperty("size")
    private int size;
    @JsonProperty("first")
    private boolean first;
    @JsonProperty("last")
    private boolean last;
    @JsonProperty("documents")
    private List<BlogSearchResultRes.DocumentResData> documents;

    @Builder
    public BlogSearchResultRes(int currentPage, int totalCount, int pageableCount, int size, boolean first, boolean last, List<DocumentResData> documents) {
        this.currentPage = currentPage;
        this.totalCount = totalCount;
        this.pageableCount = pageableCount;
        this.size = size;
        this.first = first;
        this.last = last;
        this.documents = documents;
    }

    /**
     * 블로그 검색 결과 내용
     */
    @Getter
    @NoArgsConstructor
    public static class DocumentResData {
        @JsonProperty("title")
        private String title;
        @JsonProperty("contents")
        private String contents;
        @JsonProperty("url")
        private String url;
        @JsonProperty("blogName")
        private String blogName;
        @JsonProperty("thumbnail")
        private String thumbnail;
        @JsonProperty("datetime")
        private LocalDateTime datetime;

        @Builder
        public DocumentResData(String title, String contents, String url, String blogName, String thumbnail, LocalDateTime datetime) {
            this.title = title;
            this.contents = contents;
            this.url = url;
            this.blogName = blogName;
            this.thumbnail = thumbnail;
            this.datetime = datetime;
        }
    }
}
