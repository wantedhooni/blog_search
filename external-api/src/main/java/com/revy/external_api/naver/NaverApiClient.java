package com.revy.external_api.naver;

import com.revy.core.enums.blog.BlogSort;
import com.revy.external_api.naver.exception.NaverApi4xxException;
import com.revy.external_api.naver.properties.NaverApiProperties;
import com.revy.external_api.naver.res.NaverApiErrorRes;
import com.revy.external_api.naver.res.NaverBlogSearchRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Created by Revy on 2023.11.13
 * 네이버 API 호출 클라이언트
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
                .onStatus(HttpStatusCode::is4xxClientError, response -> {
                    return response.bodyToMono(NaverApiErrorRes.class)
                            .flatMap(errorResponse -> Mono.error(new NaverApi4xxException(errorResponse.getErrorCode(), errorResponse.getErrorMessage())));
                })
                .onStatus(HttpStatusCode::is5xxServerError, response -> {
                    return response.bodyToMono(NaverApiErrorRes.class)
                            .flatMap(errorResponse -> Mono.error(new NaverApi4xxException(errorResponse.getErrorCode(), errorResponse.getErrorMessage())));
                })
                .bodyToMono(NaverBlogSearchRes.class)
                .block();
    }

    private String convertSortValue(BlogSort blogSort) {
        return switch (blogSort) {
            case ACCURACY -> "sim";
            case RECENCY -> "date";
            case null -> "sim";
            default -> "sim";
        };
    }

}
