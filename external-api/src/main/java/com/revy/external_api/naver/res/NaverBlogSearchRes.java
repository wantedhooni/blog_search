package com.revy.external_api.naver.res;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

/**
 * Created by Revy on 2023.11.13
 * 네이버 블로그 검색 API Response
 */
@Getter
@NoArgsConstructor
@ToString
public class NaverBlogSearchRes {

    @JsonProperty("total")
    private int total;
    @JsonProperty("start")
    private int start;
    @JsonProperty("display")
    private int display;
    @JsonProperty("items")
    private List<Item> items = Collections.emptyList();

    @Getter
    @NoArgsConstructor
    @ToString
    public static class Item {
        @JsonProperty("title")
        private String title;
        @JsonProperty("link")
        private String link;
        @JsonProperty("description")
        private String description;
        @JsonProperty("bloggername")
        private String bloggerName;
        @JsonProperty("bloggerlink")
        private String bloggerLink;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
        @JsonProperty("postdate")
        private LocalDate postDate;
    }
}
