package com.revy.api_server.service.impl;

import com.revy.api_server.service.data.PopularSearchesResultData;
import com.revy.domain.blog_search_statistics.entity.BlogSearchStatistics;
import com.revy.domain.blog_search_statistics.service.BlogSearchStatisticsReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Created by Revy on 2023.11.15
 */
@ExtendWith(MockitoExtension.class)
class BlogRankingServiceImplTest {

    @InjectMocks
    private BlogRankingServiceImpl blogRankingService;

    @Mock
    private BlogSearchStatisticsReader blogSearchStatisticsReader;

    private List<BlogSearchStatistics> mockBlogSearchStatistics;

    int size = 10;

    @BeforeEach
    void setUp() {
        mockBlogSearchStatistics = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            mockBlogSearchStatistics.add(new BlogSearchStatistics("TEST" + i, 100 - i, LocalDateTime.now(), LocalDateTime.now()));
        }
    }

    @Test
    void 인기_검색어_조회() {
        // given
        Mockito.when(blogSearchStatisticsReader.getPopularSearches(size)).thenReturn(mockBlogSearchStatistics);

        // when
        List<PopularSearchesResultData> popularSearches = blogRankingService.getPopularSearches(size);

        // then
        assertFalse(popularSearches.isEmpty());
        assertEquals(popularSearches.size(), size);
        assertEquals(popularSearches.getFirst().getRank(), 1);
        assertEquals(popularSearches.getLast().getRank(), size);
    }

}