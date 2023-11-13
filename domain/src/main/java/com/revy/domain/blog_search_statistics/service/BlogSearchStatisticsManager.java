package com.revy.domain.blog_search_statistics.service;

import com.revy.domain.blog_search_statistics.entity.BlogSearchStatistics;

import java.util.List;

/**
 * Created by Revy on 2023.11.12
 */
public interface BlogSearchStatisticsManager {
    void increaseCount(String keyword);

    List<BlogSearchStatistics> getPopularSearches(int size);
}