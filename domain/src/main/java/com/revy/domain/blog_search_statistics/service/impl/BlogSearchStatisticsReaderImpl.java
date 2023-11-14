package com.revy.domain.blog_search_statistics.service.impl;

import com.revy.domain.blog_search_statistics.entity.BlogSearchStatistics;
import com.revy.domain.blog_search_statistics.repository.BlogSearchStatisticsCustomRepository;
import com.revy.domain.blog_search_statistics.service.BlogSearchStatisticsReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Revy on 2023.11.14
 * 블로그 검색 통계 Reader 구현체
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BlogSearchStatisticsReaderImpl implements BlogSearchStatisticsReader {
    private final BlogSearchStatisticsCustomRepository blogSearchStatisticsCustomRepository;

    @Override
    public List<BlogSearchStatistics> getPopularSearches(int size) {
        return blogSearchStatisticsCustomRepository.findPopularSearches(size);
    }
}
