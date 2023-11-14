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

    public void increaseCount(String keyword) {
        jpaQueryFactory
                .update(BLOG_SEARCH_STATISTICS)
                .set(BLOG_SEARCH_STATISTICS.count, BLOG_SEARCH_STATISTICS.count.add(1))
                .set(BLOG_SEARCH_STATISTICS.modifyAt, LocalDateTime.now())
                .where(BLOG_SEARCH_STATISTICS.keyword.eq(keyword))
                .execute();
    }

    public List<BlogSearchStatistics> findPopularSearches(int size) {
        return jpaQueryFactory
                .selectFrom(BLOG_SEARCH_STATISTICS)
                .orderBy(BLOG_SEARCH_STATISTICS.count.desc(), BLOG_SEARCH_STATISTICS.modifyAt.desc())
                .limit(size)
                .fetch();
    }
}
