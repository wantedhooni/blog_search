package com.revy.api_server.service.impl;

import com.revy.api_server.service.BlogSearchClientService;
import com.revy.api_server.service.data.BlogSearchResultData;
import com.revy.blog_search_client.kakao.KakaoApiClient;
import com.revy.blog_search_client.kakao.res.KakaoBlogSearchRes;
import com.revy.core.enums.BlogSearchClientType;
import com.revy.core.enums.BlogSort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by Revy on 2023.11.13
 * 카카오 블로그 검색 클라이언트 서비스
 */


@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoBlogSearchClientServiceImpl implements BlogSearchClientService {

    private final KakaoApiClient kakaoApiClient;

    @Override
    public BlogSearchClientType getServiceType() {
        return BlogSearchClientType.KAKAO;
    }

    @Override
    public BlogSearchResultData searchBlog(String keyword, int size, int page, BlogSort sortCode) {
        log.debug("keyword: {}, size: {}, page: {}, sortCode: {}", keyword, size, page, sortCode);
        KakaoBlogSearchRes kakaoBlogSearchRes = kakaoApiClient.searchBlog(keyword, size, page, sortCode);
        return mapBlogSearchClientResultData(kakaoBlogSearchRes, page);
    }

    private BlogSearchResultData mapBlogSearchClientResultData(KakaoBlogSearchRes kakaoBlogSearchRes, int requestPage) {
        return BlogSearchResultData
                .builder()
                .currentPage(requestPage)
                .size(kakaoBlogSearchRes.getDocuments().size())
                .totalCount(kakaoBlogSearchRes.getMeta().getTotalCount())
                .pageableCount(kakaoBlogSearchRes.getMeta().getPageableCount())
                .first(requestPage == 1)
                .last(kakaoBlogSearchRes.getMeta().isEnd())
                .documents(kakaoBlogSearchRes.getDocuments()
                        .stream()
                        .map(doucment -> BlogSearchResultData.DocumentData
                                .builder()
                                .title(doucment.getTitle())
                                .contents(doucment.getContents())
                                .url(doucment.getUrl())
                                .blogName(doucment.getBlogName())
                                .thumbnail(doucment.getThumbnail())
                                .datetime(doucment.getDatetime())
                                .build()
                        ).toList())
                .build();
    }
}
