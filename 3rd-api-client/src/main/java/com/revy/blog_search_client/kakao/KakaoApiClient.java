package com.revy.blog_search_client.kakao;

import com.revy.blog_search_client.kakao.property.KakaoApiProperties;
import com.revy.blog_search_client.kakao.res.KakaoBlogSearchRes;
import com.revy.blog_search_client.naver.res.NaverBlogSearchRes;
import com.revy.core.enums.BlogSort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

/**
 * Created by Revy on 2023.11.13
 */

@Slf4j
@Component
public class KakaoApiClient {

    private final WebClient webClient;

    private static final String URI_BLOG_SEARCH = "/v2/search/blog";

    public KakaoApiClient(KakaoApiProperties kakaoApiPoroperties) {
        this.webClient = WebClient.builder()
                .baseUrl(kakaoApiPoroperties.getUrl())
                .defaultHeader("Authorization", "KakaoAK %s".formatted(kakaoApiPoroperties.getRestApiKey()))
                .build();
    }

    /*
query	String	검색을 원하는 질의어
특정 블로그 글만 검색하고 싶은 경우, 블로그 url과 검색어를 공백(' ') 구분자로 넣을 수 있음	O
sort	String	결과 문서 정렬 방식, accuracy(정확도순) 또는 recency(최신순), 기본 값 accuracy	X
page	Integer	결과 페이지 번호, 1~50 사이의 값, 기본 값 1	X
size	Integer	한 페이지에 보여질 문서 수, 1~50 사이의 값, 기본 값 10	X
     */
    public KakaoBlogSearchRes searchBlog(String query, int size, int page, BlogSort blogSort) {

        return webClient.get()
                .uri(uriBuilder ->
                        uriBuilder.path(URI_BLOG_SEARCH)
                                .queryParam("query", query)
                                .queryParam("size", size)
                                .queryParam("page", page)
                                .queryParam("sort", convertSortValue(blogSort))
                                .build())
                .retrieve()
                .bodyToMono(KakaoBlogSearchRes.class)
                .block();
    }

    private String convertSortValue(BlogSort blogSort) {

        return switch (blogSort) {
            case ACCURACY -> "accuracy";
            case RECENCY -> "recency";
            case null -> "accuracy";
            default -> "accuracy";
        };
    }


}
