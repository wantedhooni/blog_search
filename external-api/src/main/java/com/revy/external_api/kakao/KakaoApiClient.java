package com.revy.external_api.kakao;

import com.revy.core.enums.blog.BlogSort;
import com.revy.external_api.kakao.exception.KakaoApi4xxException;
import com.revy.external_api.kakao.exception.KakaoApi5xxException;
import com.revy.external_api.kakao.properties.KakaoApiProperties;
import com.revy.external_api.kakao.res.KakaoApiErrorRes;
import com.revy.external_api.kakao.res.KakaoBlogSearchRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

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
                .onStatus(HttpStatusCode::is4xxClientError, response -> {
                    return response.bodyToMono(KakaoApiErrorRes.class)
                            .flatMap(errorResponse -> Mono.error(new KakaoApi4xxException(errorResponse.getErrorType(), errorResponse.getErrorType())));
                })
                .onStatus(HttpStatusCode::is5xxServerError, response -> {
                    return response.bodyToMono(KakaoApiErrorRes.class)
                            .flatMap(errorResponse -> Mono.error(new KakaoApi5xxException(errorResponse.getErrorType(), errorResponse.getErrorType())));
                })
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
