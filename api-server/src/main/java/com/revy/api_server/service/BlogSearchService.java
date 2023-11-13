package com.revy.api_server.service;

import com.revy.api_server.service.data.BlogSearchConditionData;
import com.revy.api_server.service.data.BlogSearchResultData;

/**
 * Created by Revy on 2023.11.12
 * 블로그 검색 서비스
 */
public interface BlogSearchService {

    /**
     * 요청한 조건으로 블로그 검색 결과를 반환한다.
     * @param condition - 검색조건
     * @return BlogSearchResultData
     */
    BlogSearchResultData searchBlogs(BlogSearchConditionData condition);
}
