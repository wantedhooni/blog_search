package com.revy.api_server.endpoint;

import com.revy.api_server.endpoint.req.BlogRankingReq;
import com.revy.api_server.endpoint.res.BlogPopularSearchesRes;
import com.revy.api_server.service.BlogRankingService;
import com.revy.api_server.service.data.PopularSearchesResultData;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Revy on 2023.11.12
 * 블로그 인기 검색어 API
 */

@Slf4j
@Validated
@RestController
@RequestMapping("/blog/ranking")
@RequiredArgsConstructor
public class BlogRankingApi {

    private final BlogRankingService blogRankingService;


    @GetMapping("/popular-searches")
    public BlogPopularSearchesRes getPopularSearches(@ModelAttribute @Valid BlogRankingReq blogRankingReq) {


        List<PopularSearchesResultData> resultList = blogRankingService.getPopularSearches(blogRankingReq.getSize());

        return new BlogPopularSearchesRes(resultList
                .stream()
                .map(result ->
                        new BlogPopularSearchesRes
                                .BlogPopularSearchesResData(result.getRank(), result.getKeyword(), result.getCount())
                ).toList()
        );
    }

}
