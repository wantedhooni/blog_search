package com.revy.api_server.service.impl;

import com.revy.api_server.service.BlogSearchClientService;
import com.revy.api_server.service.data.BlogSearchResultData;
import com.revy.external_api.naver.NaverApiClient;
import com.revy.external_api.naver.res.NaverBlogSearchRes;
import com.revy.core.enums.BlogSearchClientType;
import com.revy.core.enums.BlogSort;
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

    @Override
    public BlogSearchClientType getServiceType() {
        return BlogSearchClientType.NAVER;
    }

    @Override
    public BlogSearchResultData searchBlog(String keyword, int size, int page, BlogSort sortCode) {
        log.debug("keyword: {}, size: {}, page: {}, sortCode: {}", keyword, size, page, sortCode);
        NaverBlogSearchRes naverBlogSearchRes = naverApiClient.searchBlog(keyword, size, page, sortCode);
        log.debug("{}", naverBlogSearchRes);
        return mapBlogSearchResultData(naverBlogSearchRes, size);
    }

    /**
     * 네이버 API 블로그 검색결과를 BlogSearchResultData로 맵핑한다.
     *
     * @param naverBlogSearchRes - 네이버 API 블로그 검색 결과
     * @param requestSize        - 요청 페이지 사이즈
     * @return BlogSearchResultData
     */
    private BlogSearchResultData mapBlogSearchResultData(NaverBlogSearchRes naverBlogSearchRes, int requestSize) {
        int pageableCount = naverBlogSearchRes.getTotal() / requestSize;
        return BlogSearchResultData
                .builder()
                .currentPage(naverBlogSearchRes.getDisplay())
                .size(naverBlogSearchRes.getItems().size())
                .totalCount(naverBlogSearchRes.getTotal())
                .pageableCount(pageableCount)
                .first(naverBlogSearchRes.getStart() == 1)
                .last(naverBlogSearchRes.getStart() == pageableCount)
                .documents(naverBlogSearchRes.getItems()
                        .stream()
                        .map(item -> BlogSearchResultData.DocumentData
                                .builder()
                                .title(item.getTitle())
                                .contents(item.getDescription())
                                .url(item.getLink())
                                .blogName(item.getBloggerName())
                                .datetime(item.getPostDate().atStartOfDay())
                                .build()
                        ).toList())
                .build();
    }
}
