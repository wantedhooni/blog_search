package com.revy.api_server.endpoint;

import com.revy.api_server.endpoint.req.BlogSearchReq;
import com.revy.api_server.endpoint.res.BlogSearchResultRes;
import com.revy.api_server.service.BlogRankingService;
import com.revy.api_server.service.BlogSearchService;
import com.revy.api_server.service.data.BlogSearchConditionData;
import com.revy.api_server.service.data.BlogSearchResultData;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/blog/search")
@RequiredArgsConstructor
public class BlogSearchApi {

    /*
    1. 블로그 검색
  - 키워드를 통해 블로그를 검색할 수 있어야 합니다.
  - 검색 결과에서 Sorting(정확도순, 최신순) 기능을 지원해야 합니다.
  - 검색 결과는 Pagination 형태로 제공해야 합니다.
  - 검색 소스는 카카오 API의 키워드로 블로그 검색(https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-blog)을 활용합니다.
  - 추후 카카오 API 이외에 새로운 검색 소스가 추가될 수 있음을 고려해야 합니다.
     */

    private final BlogSearchService blogSearchService;
    private final BlogRankingService blogRankingService;

    @GetMapping
    public BlogSearchResultRes searchBlogs(@ModelAttribute @Valid BlogSearchReq req) {
        blogRankingService.increaseCountAsync(req.getKeyword());
        BlogSearchConditionData condition = BlogSearchConditionData.builder()
                .keyword(req.getKeyword())
                .page(req.getPage())
                .size(req.getSize())
                .sort(req.getSort())
                .build();
        log.info("[getBlogs] req:{}", req);
        BlogSearchResultData result = blogSearchService.searchBlogs(condition);
        BlogSearchResultRes res = mapBlogSearchResultRes(result);
        return res;
    }

    private BlogSearchResultRes mapBlogSearchResultRes(BlogSearchResultData blogSearchResultData) {
        Assert.notNull(blogSearchResultData, "blogSearchResultData must not be null");
        return BlogSearchResultRes.builder()
                .currentPage(blogSearchResultData.getCurrentPage())
                .totalCount(blogSearchResultData.getTotalCount())
                .pageableCount(blogSearchResultData.getPageableCount())
                .size(blogSearchResultData.getSize())
                .first(blogSearchResultData.isFirst())
                .last(blogSearchResultData.isLast())
                .documents(blogSearchResultData.getDocuments().stream()
                        .map(documentData -> BlogSearchResultRes.DocumentResData.builder()
                                .title(documentData.getTitle())
                                .contents(documentData.getContents())
                                .url(documentData.getUrl())
                                .blogName(documentData.getBlogName())
                                .thumbnail(documentData.getThumbnail())
                                .datetime(documentData.getDatetime()).build()).toList())
                .build();
    }
}
