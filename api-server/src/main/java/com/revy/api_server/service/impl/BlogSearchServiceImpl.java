package com.revy.api_server.service.impl;

import com.revy.api_server.exception.InternalServerErrorException;
import com.revy.api_server.service.BlogSearchService;
import com.revy.api_server.service.data.BlogSearchConditionData;
import com.revy.api_server.service.data.BlogSearchResultData;
import com.revy.api_server.service.provider.BlogSearchClientServiceProvider;
import com.revy.core.enums.BlogSearchClientType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Created by Revy on 2023.11.12
 * 블로그 검색 서비스 구현체
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class BlogSearchServiceImpl implements BlogSearchService {

    private final BlogSearchClientServiceProvider blogSearchClientServiceProvider;

    @Override
    public BlogSearchResultData searchBlogs(BlogSearchConditionData blogSearchConditionData) {
        Assert.notNull(blogSearchConditionData, "blogSearchConditionData must be not null.");
        log.debug("blogSearchConditionData: {}", blogSearchConditionData);
        // BlogSearchClientType Enum 순서대로 동작을 수행한다.
        for (BlogSearchClientType clientType : BlogSearchClientType.values()) {
            try {
                return blogSearchClientServiceProvider
                        .getBlogSearchClientService(clientType)
                        .searchBlog(blogSearchConditionData.getKeyword(),
                                blogSearchConditionData.getSize(),
                                blogSearchConditionData.getPage(),
                                blogSearchConditionData.getSort());
            } catch (Exception e) {
                log.warn("", e);
            }
        }
        throw new InternalServerErrorException("서비스를 이용할수 없습니다. 잠시후에 이용해주세요.");
    }
}
