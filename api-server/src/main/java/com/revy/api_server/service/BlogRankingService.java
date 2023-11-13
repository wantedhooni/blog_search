package com.revy.api_server.service;

import com.revy.api_server.service.data.PopulaSearchesResultData;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * Created by Revy on 2023.11.12
 * 블로그 랭킹 서비스
 */
public interface BlogRankingService {

    /**
     * 검색어의 카운트를 증가시킨다. - 비동기
     *
     * @param keyword - 검색어
     */
    @Async
    void increaseCountAsync(String keyword);

    List<PopulaSearchesResultData> getPopularSearches(int size);
}
