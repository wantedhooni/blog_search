package com.revy.api_server.service.data;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * Created by Revy on 2023.11.12
 * 블로그 검색 서비스 결과 DTO
 */
@Getter
@NoArgsConstructor
@ToString
public class BlogSearchResultData {

    private int currentPage;
    private int totalCount;
    private int pageableCount;
    private int size;
    private boolean first;
    private boolean last;
    private List<DocumentData> documents = Collections.emptyList();

    @Builder
    public BlogSearchResultData(int currentPage, int totalCount, int pageableCount, int size, boolean first, boolean last, List<DocumentData> documents) {
        this.currentPage = currentPage;
        this.totalCount = totalCount;
        this.pageableCount = pageableCount;
        this.size = size;
        this.first = first;
        this.last = last;
        this.documents = documents;
    }

    @Getter
    @NoArgsConstructor
    @ToString
    public static class DocumentData {
        private String title;
        private String contents;
        private String url;
        private String blogName;
        private String thumbnail;
        private LocalDateTime datetime;

        @Builder
        public DocumentData(String title, String contents, String url, String blogName, String thumbnail, LocalDateTime datetime) {
            this.title = title;
            this.contents = contents;
            this.url = url;
            this.blogName = blogName;
            this.thumbnail = thumbnail;
            this.datetime = datetime;
        }
    }
}
