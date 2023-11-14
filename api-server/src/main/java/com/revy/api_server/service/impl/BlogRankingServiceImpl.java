package com.revy.api_server.service.impl;

import com.revy.api_server.service.BlogRankingService;
import com.revy.api_server.service.data.PopulaSearchesResultData;
import com.revy.domain.blog_search_statistics.entity.BlogSearchStatistics;
import com.revy.domain.blog_search_statistics.service.BlogSearchStatisticsManager;
import com.revy.domain.blog_search_statistics.service.BlogSearchStatisticsReader;
import com.revy.redis.aop.annotation.DistributedLock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Revy on 2023.11.12
 * 블로그 랭킹 서비스 구현체
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class BlogRankingServiceImpl implements BlogRankingService {

    private final BlogSearchStatisticsReader blogSearchStatisticsReader;
    private final BlogSearchStatisticsManager blogSearchStatisticsManager;


    @Override
    @DistributedLock(prefix = "BLOG_SEARCH", key = "KEYWORD", waitTime = 1000, leaseTime = 3000)
    public void increaseCountAsync(String keyword) {
        blogSearchStatisticsManager.increaseCount(keyword);
    }

    @Override
    public List<PopulaSearchesResultData> getPopularSearches(int size) {
        List<BlogSearchStatistics> blogSearchStatisticsList = blogSearchStatisticsReader.getPopularSearches(size);
        List<PopulaSearchesResultData> result = new ArrayList<>();

        for (int i = 0; i < blogSearchStatisticsList.size(); i++) {
            BlogSearchStatistics blogSearchStatistics = blogSearchStatisticsList.get(i);
            result.add(new PopulaSearchesResultData(i + 1, blogSearchStatistics.getKeyword(), blogSearchStatistics.getCount()));
        }
        return result;
    }
}
