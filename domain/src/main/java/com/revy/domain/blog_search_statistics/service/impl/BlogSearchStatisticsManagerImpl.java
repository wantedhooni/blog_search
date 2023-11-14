package com.revy.domain.blog_search_statistics.service.impl;

import com.revy.domain.blog_search_statistics.entity.BlogSearchStatistics;
import com.revy.domain.blog_search_statistics.repository.BlogSearchStatisticsCustomRepository;
import com.revy.domain.blog_search_statistics.repository.BlogSearchStatisticsRepository;
import com.revy.domain.blog_search_statistics.service.BlogSearchStatisticsManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Revy on 2023.11.12
 */

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class BlogSearchStatisticsManagerImpl implements BlogSearchStatisticsManager {
    private final BlogSearchStatisticsRepository blogSearchStatisticsRepository;
    private final BlogSearchStatisticsCustomRepository blogSearchStatisticsCustomRepository;

    @Override
    public void increaseCount(String keyword) {
        BlogSearchStatistics blogSearchStatistics = blogSearchStatisticsRepository.findByKeyword(keyword)
                .orElseGet(() -> save(keyword));
        blogSearchStatisticsCustomRepository.increaseCount(blogSearchStatistics.getKeyword());
    }

    private BlogSearchStatistics save(String keyword) {
        return blogSearchStatisticsRepository.save(new BlogSearchStatistics(keyword));
    }


}
