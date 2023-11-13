package com.revy.blog_search_client.naver;

import com.revy.blog_search_client.naver.property.NaverApiProperties;
import com.revy.blog_search_client.naver.res.NaverBlogSearchRes;
import com.revy.core.enums.BlogSort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Created by Revy on 2023.11.13
 */
@Slf4j
@Component
public class NaverApiClient {
    private final WebClient webClient;
    private static final String URI_BLOG_SEARCH = "/v1/search/blog.json";

    public NaverApiClient(NaverApiProperties naverApiProperties) {
        this.webClient = WebClient.builder()
                .baseUrl(naverApiProperties.getUrl())
                .defaultHeader("X-Naver-Client-Id", naverApiProperties.getClientId())
                .defaultHeader("X-Naver-Client-Secret", naverApiProperties.getClientSecret())
                .build();
    }

    /*
    query	String	Y	검색어. UTF-8로 인코딩되어야 합니다.
    display	Integer	N	한 번에 표시할 검색 결과 개수(기본값: 10, 최댓값: 100)
    start	Integer	N	검색 시작 위치(기본값: 1, 최댓값: 1000)
    sort	String	N	검색 결과 정렬 방법
    - sim: 정확도순으로 내림차순 정렬(기본값)
    - date: 날짜순으로 내림차순 정렬
     */

    public NaverBlogSearchRes searchBlog(String query, int display, int start, BlogSort sort) {
        return webClient.get()
                .uri(uriBuilder ->
                        uriBuilder.path(URI_BLOG_SEARCH)
                                .queryParam("query", query)
                                .queryParam("start", start)
                                .queryParam("display", display)
                                .queryParam("sort", convertSortValue(sort))
                                .build())
                .retrieve()
                .bodyToMono(NaverBlogSearchRes.class)
                .block();
    }

/*
검색 결과 정렬 방법
- sim: 정확도순으로 내림차순 정렬(기본값)
- date: 날짜순으로 내림차순 정렬
     */

    private String convertSortValue(BlogSort blogSort) {
        return switch (blogSort) {
            case ACCURACY -> "sim";
            case RECENCY -> "date";
            case null -> "sim";
            default -> "sim";
        };
    }

}
