package com.revy.api_server.service.impl;

import com.revy.api_server.service.BlogSearchClientService;
import com.revy.api_server.service.data.BlogSearchResultData;
import com.revy.blog_search_client.naver.NaverApiClient;
import com.revy.blog_search_client.naver.res.NaverBlogSearchRes;
import com.revy.core.enums.BlogSearchClientType;
import com.revy.core.enums.BlogSort;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by Revy on 2023.11.13
 * 네이버 블로그 검색 클라이언트 서비스
 */


@Slf4j
@Component
@RequiredArgsConstructor
public class NaverBlogSearchClientServiceImpl implements BlogSearchClientService {

    private final NaverApiClient naverApiClient;

    @PostConstruct
    public void init() {
        log.info("init: {}", getClass().getName());
    }

    @Override
    public BlogSearchClientType getServiceType() {
        return BlogSearchClientType.NAVER;
    }

    @Override
    public BlogSearchResultData searchBlog(String keyword, int size, int page, BlogSort sortCode) {
        log.debug("keyword: {}, size: {}, page: {}, sortCode: {}", keyword, size, page, sortCode);
        NaverBlogSearchRes naverBlogSearchRes = naverApiClient.searchBlog(keyword, size, page, sortCode);
        log.debug("{}", naverBlogSearchRes);
        return null;
    }
}
