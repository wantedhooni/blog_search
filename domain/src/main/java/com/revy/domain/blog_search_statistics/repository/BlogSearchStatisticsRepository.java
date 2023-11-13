package com.revy.domain.blog_search_statistics.repository;

import com.revy.domain.blog_search_statistics.entity.BlogSearchStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Revy on 2023.11.12
 * BlogSearchStatistics JPA 저장소
 */
public interface BlogSearchStatisticsRepository extends JpaRepository<BlogSearchStatistics, String> {

    Optional<BlogSearchStatistics> findByKeyword(String keyword);
}
