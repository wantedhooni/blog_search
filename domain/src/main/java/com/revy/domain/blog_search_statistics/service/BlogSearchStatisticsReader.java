package com.revy.domain.blog_search_statistics.service;

import com.revy.domain.blog_search_statistics.entity.BlogSearchStatistics;

import java.util.List;

/**
 * Created by Revy on 2023.11.12
 * 블로그 검색 통계 Reader
 */
public interface BlogSearchStatisticsReader {

    /**
     * 인기 검색어 랭킹을 반환한다.
     * @param size
     * @return
     */
    List<BlogSearchStatistics> getPopularSearches(int size);
}
