package com.revy.domain.blog_search_statistics.service;

/**
 * Created by Revy on 2023.11.12
 * 블로그 검색 통계 Manager
 */
public interface BlogSearchStatisticsManager {
    /**
     * 카워드를 블로그 검색 통계에 저장 이후 카운터를 1 증가시킨다.
     * @param keyword
     */
    void increaseCount(String keyword);
}
