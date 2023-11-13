package com.revy.api_server.endpoint;

import com.revy.api_server.endpoint.res.BlogPopularSearchesRes;
import com.revy.api_server.service.BlogRankingService;
import com.revy.api_server.service.data.PopulaSearchesResultData;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Revy on 2023.11.12
 * 블로그 인기 검색어 API
 */

@Slf4j
@RestController
@RequestMapping("/blog/ranking")
@RequiredArgsConstructor
public class BlogRankingApi {

    private final BlogRankingService BlogRankingService;

    @GetMapping("/popular/searches")
    public BlogPopularSearchesRes getPopularSearches(
            @RequestParam(name = "size", defaultValue = "10", required = false) @Min(1) @Max(10) int size) {
        List<PopulaSearchesResultData> resultList = BlogRankingService.getPopularSearches(size);

        return new BlogPopularSearchesRes(resultList
                .stream()
                .map(result ->
                        new BlogPopularSearchesRes.BlogPopularSearchesResData(result.getRank(), result.getKeyword(), result.getCount())
                ).toList()
        );
    }

}
