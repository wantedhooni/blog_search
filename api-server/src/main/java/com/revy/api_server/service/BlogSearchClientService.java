package com.revy.api_server.service;

import com.revy.api_server.service.data.BlogSearchResultData;
import com.revy.core.enums.blog.BlogSearchClientType;
import com.revy.core.enums.blog.BlogSort;

/**
 * Created by Revy on 2023.11.13
 * 블로그 검색 Client 서비스
 */
public interface BlogSearchClientService {

    /**
     * 블로그 검색 클라이언트의 타입을 반환한다.
     * @return BlogSearchClientType
     */
    BlogSearchClientType getServiceType();

    /**
     * 블로그 검색 결과를 반환한다.
     * @param keyword - 키워드
     * @param size - 사이즈
     * @param page - 페이지
     * @param sortCode - 정렬
     * @return BlogSearchResultData
     */
    BlogSearchResultData searchBlog(String keyword, int size, int page, BlogSort sortCode);
}
