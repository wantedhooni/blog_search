package com.revy.domain.blog_search_statistics.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


/**
 * Created by Revy on 2023.11.12
 * 블로그 검색 통계 테이블
 */

@Entity
@Table(name = "blog_Search_statistics",
        indexes = { @Index(name= "IDX_COUNT_MODIFY_AT", columnList = "count, modify_at")}
)
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class BlogSearchStatistics {

    @Id
    @Column(name = "keyword", nullable = false, unique = true)
    private String keyword;

    @Column(name = "count", nullable = false, updatable = false)
    private int count;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "modify_at", nullable = false)
    private LocalDateTime modifyAt;

    public BlogSearchStatistics(String keyword) {
        this.keyword = keyword;
    }

    public BlogSearchStatistics(String keyword, int count, LocalDateTime createdAt, LocalDateTime modifyAt) {
        this.keyword = keyword;
        this.count = count;
        this.createdAt = createdAt;
        this.modifyAt = modifyAt;
    }
}
