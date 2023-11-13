package com.revy.blog_search_client.kakao.res;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * Created by Revy on 2023.11.13
 */

@Getter
@NoArgsConstructor
@ToString
public class KakaoBlogSearchRes {

    @JsonProperty("documents")
    private List<Doucment> documents = Collections.emptyList();
    @JsonProperty("meta")
    private meta meta;


    @Getter
    @NoArgsConstructor
    @ToString
    public static class meta {
        @JsonProperty("total_count")
        private int totalCount;
        @JsonProperty("pageable_count")
        private int pageableCount;
        @JsonProperty("is_end")
        private boolean end;
    }

    @Getter
    @NoArgsConstructor
    @ToString
    public static class Doucment {
        @JsonProperty("blogname")
        private String blogName;
        @JsonProperty("contents")
        private String contents;
        @JsonProperty("thumbnail")
        private String thumbnail;
        @JsonProperty("title")
        private String title;
        @JsonProperty("url")
        private String url;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
        @JsonProperty("datetime")
        private LocalDateTime datetime;
    }
}
