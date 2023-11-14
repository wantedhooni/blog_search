package com.revy.domain.blog_search_statistics.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.revy.domain.blog_search_statistics.entity.BlogSearchStatistics;
import com.revy.domain.blog_search_statistics.entity.QBlogSearchStatistics;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Revy on 2023.11.12
 * QueryDsl 커스텀 쿼리 저장소
 */

@Repository
@RequiredArgsConstructor
public class BlogSearchStatisticsCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private static final QBlogSearchStatistics BLOG_SEARCH_STATISTICS = QBlogSearchStatistics.blogSearchStatistics;

    /**
     * 검색 키워드의 카운터를 1 증가하는 쿼리
     * @param keyword - 키워드
     */
    public void increaseCount(String keyword) {
        jpaQueryFactory
                .update(BLOG_SEARCH_STATISTICS)
                .set(BLOG_SEARCH_STATISTICS.count, BLOG_SEARCH_STATISTICS.count.add(1))
                .set(BLOG_SEARCH_STATISTICS.modifyAt, LocalDateTime.now())
                .where(BLOG_SEARCH_STATISTICS.keyword.eq(keyword))
                .execute();
    }

    /**
     * 인기검색어를 검색한다.
     * @param size
     * @return BlogSearchStatistics
     */
    public List<BlogSearchStatistics> findPopularSearches(int size) {
        return jpaQueryFactory
                .selectFrom(BLOG_SEARCH_STATISTICS)
                .orderBy(BLOG_SEARCH_STATISTICS.count.desc(), BLOG_SEARCH_STATISTICS.modifyAt.desc())
                .limit(size)
                .fetch();
    }
}
